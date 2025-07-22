package com.faca_receita.recipe.controllers;

import com.faca_receita.ingredients.models.Ingredient;
import com.faca_receita.recipe.dtos.RecipeListResponseDTO;
import com.faca_receita.recipe.dtos.RecipeOptionDTO;
import com.faca_receita.recipe.dtos.SearchRecipeDTO;
import com.faca_receita.recipe.models.Recipe;
import com.faca_receita.recipe.services.RecipeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Recipe> save(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @PostMapping("/search")
    public ResponseEntity<List<RecipeListResponseDTO>> search(@RequestBody SearchRecipeDTO dto) {
        return ResponseEntity.ok(this.recipeService.search(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.recipeService.getById(id));
    }

    @GetMapping("/option-list")
    public ResponseEntity<List<RecipeOptionDTO>> getRecipeOptionList() {
        return ResponseEntity.ok(this.recipeService.getRecipeOptionList());
    }

    @GetMapping("/get-ingredients-by-recipe-id/{id}")
    public ResponseEntity<List<Ingredient>> getIngredientsByRecipeId(@PathVariable Long id) {
        return ResponseEntity.ok(this.recipeService.getIngredientsByRecipeId(id));
    }
}
