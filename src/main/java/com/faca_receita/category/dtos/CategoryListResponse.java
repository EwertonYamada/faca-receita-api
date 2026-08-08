package com.faca_receita.category.dtos;

public record CategoryListResponse(
        Long id,
        String category,
        String description,
        boolean active
) {
}
