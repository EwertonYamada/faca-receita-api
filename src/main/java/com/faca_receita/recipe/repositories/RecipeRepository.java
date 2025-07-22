package com.faca_receita.recipe.repositories;

import com.faca_receita.ingredients.models.Ingredient;
import com.faca_receita.recipe.dtos.RecipeOptionDTO;
import com.faca_receita.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(nativeQuery = true,
        value = " SELECT " +
                "   r.id, " +
                "   r.name " +
                " FROM recipe r ")
    List<RecipeOptionDTO> getRecipeOptionList();

    @Query(nativeQuery = true,
        value = " SELECT " +
                "   * " +
                " FROM ingredient i " +
                " WHERE recipe_id = :id ")
    List<Ingredient> getIngredientsByRecipeId(@Param("id")Long id);
}
