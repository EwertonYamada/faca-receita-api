package com.faca_receita.recipe.models;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import com.faca_receita.ingredients.models.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "yield_type")
    @Enumerated(EnumType.STRING)
    private MeasureUnit yieldType;

    @Column(name = "recipe_yield")
    private String recipeYield;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "recipe_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "preparation_instructions")
    private String preparationInstructions;

    @Column(name = "created_at")
    private Date createdAt;
}