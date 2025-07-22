package com.faca_receita.auth.dtos;

public class AuthDTO {
    public record AuthRequest(String email, String password) {}
    public record AuthResponse(String token) {}
}
