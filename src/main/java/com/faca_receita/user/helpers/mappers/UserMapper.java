package com.faca_receita.user.helpers.mappers;

import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserMapper {
    public static User toEntity(CreateUserDTO userDTO, String hashPassword) {
        User user = new User();
        user.setDocument(userDTO.getDoc());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhone());
        user.setEmailVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPasswordHash(hashPassword);
        return user;
    }

    public static CreateUserResponseDTO toResponse(User user) {
        CreateUserResponseDTO response = new CreateUserResponseDTO();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setEmailVerified(user.getEmailVerified());
        return response;
    }
}
