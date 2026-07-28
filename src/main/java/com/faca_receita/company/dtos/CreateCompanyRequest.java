package com.faca_receita.company.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCompanyRequest(
        @NotBlank(message = "Razão social é obrigatória")
        String legalName,
        String description,
        @NotBlank(message = "Telefone é obrigatória")
        String phoneNumber,
        @Email  @NotBlank(message = "E-mail é obrigatória")
        String email,
        String tradeName,
        @NotBlank(message = "CNPJ é obrigatória")
        String cnpj,
        String stateTaxRegistration,
        String municipalTaxRegistration,
        String whatsapp,
        String instagram,
        String facebook,
        String website,
        String primaryColor,
        String logoUrl,
        Long userId
) {
}
