package com.faca_receita.recipe.services;

import com.faca_receita.recipe.dtos.RecipeListResponseDTO;
import com.faca_receita.recipe.dtos.SearchRecipeDTO;
import com.faca_receita.recipe.models.Recipe;
import com.faca_receita.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeQueryService recipeQueryService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         RecipeQueryService recipeQueryService) {
        this.recipeRepository = recipeRepository;
        this.recipeQueryService = recipeQueryService;
    }

    public Recipe save(Recipe recipe) {
        return this.recipeRepository.save(recipe);
    }

    @Transactional(readOnly = true)
    public List<RecipeListResponseDTO> search(SearchRecipeDTO dto) {
        return this.recipeQueryService.searchRecipes(dto);
    }
}
