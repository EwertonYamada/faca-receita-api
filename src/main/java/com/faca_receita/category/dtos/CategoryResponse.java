package com.faca_receita.category.dtos;

public record CategoryResponse (
        Long id,
        String category,
        String description,
        boolean active
) { }