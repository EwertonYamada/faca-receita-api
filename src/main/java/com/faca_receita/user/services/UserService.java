package com.faca_receita.user.services;

import com.faca_receita.user.dtos.CreateUserDTO;
import com.faca_receita.user.models.User;
import com.faca_receita.user.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public void validations(CreateUserDTO createUserDTO) {
        this.validateIfUserAlreadyExists(createUserDTO);
        this.validateIfPasswordAndConfirmationMatch(createUserDTO.getPassword(), createUserDTO.getPasswordConfirmation());
    }

    private void validateIfUserAlreadyExists(CreateUserDTO createUserDTO) {
        Boolean userAlreadyExists = this.userRepository.existingUserByDocumentOrEmail(createUserDTO.getEmail(), createUserDTO.getDoc());
        if (userAlreadyExists) {
            throw new IllegalStateException("Já existe um usuário com os dados informados!");
        }
    }

    public void validateIfPasswordAndConfirmationMatch(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Senha e confirmação de senha não coincidem!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                List.of()
        );
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("Usuário não encontrado!"));
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public void validateAccountConfirmation(String email) {
        Boolean confirmed = this.userRepository.isAccountConfirmation(email);
        if (!confirmed) {
            throw new IllegalStateException("Conta não confirmada! Verifique o seu e-mail.");
        }
    }
}
