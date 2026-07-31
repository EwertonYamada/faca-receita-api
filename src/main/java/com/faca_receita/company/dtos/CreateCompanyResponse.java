package com.faca_receita.company.dtos;

import java.time.LocalDateTime;

public record CreateCompanyResponse(
        Long id,
        Long userId,
        String legalName,
        String tradeName,
        String description,
        String cnpj,
        String stateTaxRegistration,
        String municipalTaxRegistration,
        String phoneNumber,
        String whatsapp,
        String email,
        String website,
        String instagram,
        String facebook,
        String logoUrl,
        String primaryColor,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
