package com.faca_receita.auth.services;

import com.faca_receita.auth.dtos.AuthDTO;
import com.faca_receita.auth.dtos.ForgotPasswordDTO;
import com.faca_receita.auth.models.UserToken;
import com.faca_receita.auth.repositories.UserTokenRepository;
import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.helpers.mappers.UserMapper;
import com.faca_receita.user.models.User;
import com.faca_receita.user.services.EmailService;
import com.faca_receita.user.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserTokenRepository userTokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(
            AuthenticationManager authenticationManager,
            UserService userService,
            UserTokenRepository userTokenRepository,
            EmailService emailService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userTokenRepository = userTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthDTO.AuthResponse getToken(AuthDTO.AuthRequest authRequest) {
        String email = authRequest.email();
        String password = authRequest.password();
        this.validateIfEmailExists(email);
        this.validateAccountConfirmation(authRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return new AuthDTO.AuthResponse(this.jwtService.generateToken(user));
    }

    private void validateIfEmailExists(String email) {
        this.userService.findByEmail(email);
    }

    private void validateAccountConfirmation(AuthDTO.AuthRequest authRequest) {
        this.userService.validateAccountConfirmation(authRequest.email());
    }

    public void save(UserToken token) {
        this.userTokenRepository.save(token);
    }

    public UserToken findByToken(String token) {
        return this.userTokenRepository.findByToken(token);
    }

    public Boolean isValidToken(UserToken token) {
        return !LocalDateTime.now().isAfter(token.getExpirationDate()) && !token.getUsed();
    }

    @Transactional
    public CreateUserResponseDTO registerUser(CreateUserDTO createUserDTO) {
        this.userService.validations(createUserDTO);

        User user = UserMapper.toEntity(createUserDTO, passwordEncoder.encode(createUserDTO.getPassword()));
        this.userService.save(user);
        UserToken token = new UserToken(user, LocalDateTime.now().plusHours(24));
        this.save(token);
        this.buildAndSendConfirmationEmail(user, token);
        return UserMapper.toResponse(user);
    }

    public void buildAndSendConfirmationEmail(User user, UserToken token) {
        String subject = "Confirmação de cadastro";
        String to = user.getEmail();
        String link = "http://localhost:8080/api/auth/confirm?token=" + token.getToken();
        String message = String.format(
                "Olá %s,%n%n" +
                "Bem-vindo(a) ao Faça Receita!%n" +
                "Para ativar sua conta, clique no link abaixo:%n%n" +
                "%s%n%n" +
                "Este link é válido por 24 horas e só pode ser usado uma vez.%n" +
                "Se você não criou esta conta, ignore este e-mail.%n%n" +
                "Atenciosamente,%n" +
                "Equipe Faça Receita!",
                user.getUsername(),
                link
        );

        this.emailService.sendAuthEmail(subject, to, message);
    }

    @Transactional
    public void confirmUser(String token) {
        UserToken userToken = this.findByToken(token);
        this.validateToken(userToken);

        User user = userToken.getUser();
        user.setEmailVerified(true);
        this.userService.save(user);

        userToken.setUsed(true);
        this.userTokenRepository.save(userToken);
    }

    private void validateToken(UserToken token) {
        if (token != null && !this.isValidToken(token))
            throw new RuntimeException("Token inválido ou expirado");
    }

    public String sendResetEmail(String email) {
        try {
            User user = this.userService.findByEmail(email);
            UserToken resetToken = new UserToken(user, LocalDateTime.now().plusMinutes(15));
            this.userTokenRepository.save(resetToken);
            this.buildAndSendResetEmail(user, resetToken);
            return "Enviado e-mail para " + email + " com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void buildAndSendResetEmail(User user, UserToken resetToken) {
        String subject = "Redefinir senha";
        String to = user.getEmail();
        String link = "http://localhost:4200/reset-password?email=" + to + "&token=" + resetToken.getToken();
        String message = String.format(
                "Olá %s,%n%n" +
                "Recebemos uma solicitação para redefinir a senha da sua conta.%n" +
                "Para criar uma nova senha, clique no link abaixo:%n%n" +
                "%s%n%n" +
                "Este link é válido por 15 minutos e só pode ser usado uma vez.%n" +
                "Se você não solicitou a redefinição, ignore este e-mail.%n%n" +
                "Atenciosamente,%n" +
                "Equipe Faça Receita",
                user.getUsername(),
                link
        );

        this.emailService.sendAuthEmail(subject, to, message);
    }

    public void createNewPassword(ForgotPasswordDTO newPasswordDTO) {
        UserToken userToken = this.findByToken(newPasswordDTO.token());
        this.validateToken(userToken);
        this.userService.validateIfPasswordAndConfirmationMatch(newPasswordDTO.password(), newPasswordDTO.passwordConfirmation());

        User user = userToken.getUser();
        user.setPasswordHash(passwordEncoder.encode(newPasswordDTO.password()));
        this.userService.save(user);

        userToken.setUsed(true);
        this.userTokenRepository.save(userToken);
    }
}
