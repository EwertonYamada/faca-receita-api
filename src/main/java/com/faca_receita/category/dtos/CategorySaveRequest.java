package com.faca_receita.category.dtos;


import jakarta.validation.constraints.NotBlank;

public record CategorySaveRequest (
        String id,
        @NotBlank(message = "A categoria é obrigatória")
        String category,
        String description
) {
}
