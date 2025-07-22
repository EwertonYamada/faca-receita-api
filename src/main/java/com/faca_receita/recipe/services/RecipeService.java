package com.faca_receita.recipe.services;

import com.faca_receita.ingredients.models.Ingredient;
import com.faca_receita.recipe.dtos.RecipeListResponseDTO;
import com.faca_receita.recipe.dtos.RecipeOptionDTO;
import com.faca_receita.recipe.dtos.SearchRecipeDTO;
import com.faca_receita.recipe.models.Recipe;
import com.faca_receita.recipe.repositories.RecipeRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeQueryService recipeQueryService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         RecipeQueryService recipeQueryService, Validator validator) {
        this.recipeRepository = recipeRepository;
        this.recipeQueryService = recipeQueryService;
    }

    @Transactional
    public Recipe save(Recipe recipe) {
        recipe.setCreatedAt(new Date());
        return recipeRepository.save(recipe);
    }

    @Transactional(readOnly = true)
    public List<RecipeListResponseDTO> search(SearchRecipeDTO dto) {
        return this.recipeQueryService.searchRecipes(dto);
    }

    @Transactional(readOnly = true)
    public Recipe getById(Long id) {
        Recipe recipe = this.recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        return recipe;
    }

    public List<RecipeOptionDTO> getRecipeOptionList() {
        return this.recipeRepository.getRecipeOptionList();
    }

    public List<Ingredient> getIngredientsByRecipeId(Long id) {
        return this.recipeRepository.getIngredientsByRecipeId(id);
    }
}
