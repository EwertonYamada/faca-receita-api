package com.faca_receita.user.services;

import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.helpers.mappers.UserMapper;
import com.faca_receita.user.models.User;
import com.faca_receita.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Transactional
    public CreateUserResponseDTO registerUser(CreateUserDTO createUserDTO) {
        this.validations(createUserDTO);
        User user = UserMapper.toEntity(createUserDTO, passwordEncoder);
        this.userRepository.save(user);
        this.emailService.sendConfirmationEmail(user.getEmail(), user.getVerificationToken());
        return UserMapper.toResponse(user);
    }

    private void validations(CreateUserDTO createUserDTO) {
        this.validateIfUserAlreadyExists(createUserDTO);
    }

    private void validateIfUserAlreadyExists(CreateUserDTO createUserDTO) {
        Boolean userAlreadyExists = this.userRepository.existingUserByDocumentOrEmail(createUserDTO.getEmail(), createUserDTO.getDoc());
        if (userAlreadyExists) {
            throw new IllegalStateException("There is already a user with the document or email provided");
        }
    }

    public void confirmUser(String token) {
        User user = this.userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                List.of()
        );
    }
}
