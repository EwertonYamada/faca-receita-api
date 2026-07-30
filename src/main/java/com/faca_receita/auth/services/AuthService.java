package com.faca_receita.auth.services;

import com.faca_receita.auth.dtos.AuthDTO;
import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthService {

    private final SecretKey secretKey;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(
            @Value("${jwt.secret}") String secret,
            AuthenticationManager authenticationManager, UserService userService
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public AuthDTO.AuthResponse getToken(AuthDTO.AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return new AuthDTO.AuthResponse(this.generateToken(user));
    }

    private String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities().stream()
                        .map(a -> a.getAuthority()).toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public CreateUserResponseDTO registerUser(CreateUserDTO createUserDTO) {
        return this.userService.registerUser(createUserDTO);
    }

    public void confirmUser(String token) {
        this.userService.confirmUser(token);
    }
}
