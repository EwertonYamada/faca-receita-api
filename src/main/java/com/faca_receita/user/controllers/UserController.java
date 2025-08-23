package com.faca_receita.user.controllers;

import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<CreateUserResponseDTO> newUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(this.userService.createUser(createUserDTO));
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        this.userService.confirmUser(token);
        return ResponseEntity.ok("Conta confirmada com sucesso!");
    }
}
