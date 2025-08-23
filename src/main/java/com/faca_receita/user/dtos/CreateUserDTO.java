package com.faca_receita.user.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDTO {
    @NotBlank(message = "O nome do usuário é obrigatório")
    private String name;
    @NotBlank(message = "O documento do usuário é obrigatório")
    private String doc;
    @NotBlank(message = "O e-mail do usuário é obrigatório")
    private String email;
    @NotBlank(message = "O telefone do usuário é obrigatório")
    private String phone;
    @NotBlank(message = "A senha do usuário é obrigatório")
    private String password;
    @NotBlank(message = "A confirmação de senha do usuário é obrigatório")
    private String passwordConfirmation;
}
