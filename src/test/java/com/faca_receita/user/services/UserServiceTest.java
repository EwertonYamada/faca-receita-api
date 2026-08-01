package com.faca_receita.user.services;

import com.faca_receita.auth.services.AuthService;
import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.dtos.CreateUserResponseDTO;
import com.faca_receita.user.models.User;
import com.faca_receita.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private CreateUserDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new CreateUserDTO();
        dto.setName("Ewerton");
        dto.setEmail("teste@teste.com");
        dto.setDoc("12345678900");
        dto.setPassword("Senha123!");
    }

    @Test
    void shouldSaveUserWhenValid() {
        when(userRepository.existingUserByDocumentOrEmail(dto.getEmail(), dto.getDoc()))
                .thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword()))
                .thenReturn("hashedPassword");
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateUserResponseDTO response = authService.registerUser(dto);

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(dto.getEmail(), response.getEmail());
        assertEquals(dto.getName(), response.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        when(userRepository.existingUserByDocumentOrEmail(dto.getEmail(), dto.getDoc()))
                .thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.registerUser(dto));

        assertEquals("There is already a user with the document or email provided",
                exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}
