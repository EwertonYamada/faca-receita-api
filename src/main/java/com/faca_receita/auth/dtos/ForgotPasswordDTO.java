package com.faca_receita.auth.dtos;

public record ForgotPasswordDTO(
    String email,
    String password,
    String passwordConfirmation,
    String token) {}
