package com.faca_receita.recipe.models;

import com.faca_receita.helpers.generalEnums.MeasureUnit;
import com.faca_receita.helpers.generalEnums.ProductCategory;
import com.faca_receita.ingredients.models.Ingredient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Categoria é obrigatória")
    private ProductCategory category;

    @Column(name = "yield_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Categoria é obrigatória")
    private MeasureUnit yieldType;

    @Column(name = "recipe_yield")
    @NotNull(message = "Categoria é obrigatória")
    private String recipeYield;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    @JsonManagedReference
    @NotNull(message = "Ingredientes são obrigatórios")
    @Size(min = 1, message = "A receita deve ter pelo menos um ingrediente")
    private List<Ingredient> ingredients;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "preparation_instructions")
    private String preparationInstructions;

    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}