package com.faca_receita.recipe.dtos;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import com.faca_receita.helpers.generalEnums.ProductCategory;

import java.util.Date;

public record RecipeListResponseDTO(
    Long id,
    String name,
    ProductCategory category,
    MeasureUnit yieldType,
    String recipeYield,
    String preparationTime,
    Date createdAt
    ) {}