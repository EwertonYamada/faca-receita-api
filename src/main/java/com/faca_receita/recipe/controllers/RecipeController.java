package com.faca_receita.recipe.controllers;

import com.faca_receita.recipe.dtos.RecipeListResponseDTO;
import com.faca_receita.recipe.dtos.SearchRecipeDTO;
import com.faca_receita.recipe.models.Recipe;
import com.faca_receita.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @PostMapping("/search")
    public ResponseEntity<List<RecipeListResponseDTO>> search(@RequestBody SearchRecipeDTO dto) {
        return ResponseEntity.ok(this.recipeService.search(dto));
    }
}
