package com.faca_receita.ingredients.models;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import com.faca_receita.recipe.models.Recipe;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "measurement_unit")
    @Enumerated(value = EnumType.STRING)
    private MeasureUnit measurementUnit;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;
}
