package com.faca_receita.ingredients.models;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "quantity")
    private String quantity;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MeasureUnit measurementUnit;
}
