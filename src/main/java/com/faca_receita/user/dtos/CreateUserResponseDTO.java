package com.faca_receita.user.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserResponseDTO {
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean emailVerified;
}
