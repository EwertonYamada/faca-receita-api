package com.faca_receita.recipe.dtos;

import com.faca_receita.helpers.generalEnums.MeasureUnit;

import java.util.Date;

public record RecipeListResponseDTO(
    Long id,
    String name,
    String category,
    MeasureUnit yieldType,
    String recipeYield,
    String preparationTime,
    Date createdAt
    ) {}