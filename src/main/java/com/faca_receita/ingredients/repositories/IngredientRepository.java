package com.faca_receita.ingredients.repositories;

import com.faca_receita.ingredients.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
}
