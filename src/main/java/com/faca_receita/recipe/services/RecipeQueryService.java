package com.faca_receita.recipe.services;

import com.faca_receita.recipe.dtos.RecipeListResponseDTO;
import com.faca_receita.recipe.dtos.SearchRecipeDTO;
import com.faca_receita.recipe.models.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RecipeQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RecipeListResponseDTO> searchRecipes(SearchRecipeDTO dto) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipeListResponseDTO> criteriaQuery = criteriaBuilder.createQuery(RecipeListResponseDTO.class);
        Root<Recipe> root = criteriaQuery.from(Recipe.class);
        List<Predicate> predicates = this.buildPredicates(criteriaBuilder, root, dto);
        criteriaQuery.select(criteriaBuilder.construct(
                RecipeListResponseDTO.class,
                root.get("id"),
                root.get("name"),
                root.get("category"),
                root.get("yieldType"),
                root.get("recipeYield"),
                root.get("preparationTime"),
                root.get("createdAt")
        ));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder criteriaBuilder, Root<Recipe> root, SearchRecipeDTO dto) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(dto.getRecipeName()) && !dto.getRecipeName().isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + dto.getRecipeName().toLowerCase() + "%"));
        }

        if (Objects.nonNull(dto.getRecipeDateStart())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), dto.getRecipeDateStart()));
        }

        if (Objects.nonNull(dto.getRecipeDateEnd())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), dto.getRecipeDateEnd()));
        }
        return predicates;
    }
}
