package com.faca_receita.auth.controllers;

import com.faca_receita.auth.dtos.AuthDTO;
import com.faca_receita.auth.services.AuthService;
import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO.AuthResponse> login(@RequestBody AuthDTO.AuthRequest loginRequest) {
        return ResponseEntity.ok(this.authService.getToken(loginRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(this.authService.registerUser(createUserDTO));
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        this.authService.confirmUser(token);
        return ResponseEntity.ok("Conta confirmada com sucesso!");
    }
}
