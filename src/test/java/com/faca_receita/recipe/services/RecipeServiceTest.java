package com.faca_receita.recipe.services;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import com.faca_receita.helpers.generalEnums.ProductCategory;
import com.faca_receita.ingredients.models.Ingredient;
import com.faca_receita.recipe.models.Recipe;
import com.faca_receita.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeQueryService recipeQueryService;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe getValidRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Brigadeiro");
        recipe.setCategory(ProductCategory.SWEET);
        recipe.setYieldType(MeasureUnit.UNIT);
        recipe.setRecipeYield("12");
        recipe.setDescription("Brigadeiro tradicional");
        recipe.setPreparationTime("15");
        recipe.setPreparationInstructions("Misture e leve ao fogo.");
        recipe.setIngredients(List.of(getValidIngredient()));
        return recipe;
    }

    private Ingredient getValidIngredient() {
        Ingredient ing = new Ingredient();
        ing.setIngredient("Leite condensado");
        ing.setQuantity("395");
        ing.setMeasurementUnit(MeasureUnit.GRAM);
        return ing;
    }

    @Test
    void shouldSaveValidRecipe() {
        Recipe recipe = getValidRecipe();
        when(recipeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Recipe saved = recipeService.save(recipe);

        assertNotNull(saved.getCreatedAt());
        assertEquals("Brigadeiro", saved.getName());
        assertEquals(ProductCategory.SWEET, saved.getCategory());
        assertEquals(1, saved.getIngredients().size());

        verify(recipeRepository).save(recipe);
    }
}
