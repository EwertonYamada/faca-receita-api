package com.faca_receita.ingredients.models;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import jakarta.persistence.*;

@Entity

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public MeasureUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasureUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
