package com.faca_receita.category.repositories;

import com.faca_receita.category.dtos.CategoryListResponse;
import com.faca_receita.category.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
                SELECT new com.faca_receita.category.dtos.CategoryListResponse(
                    c.id,
                    c.category,
                    c.description,
                    c.active
                )
                FROM Category c
                WHERE LOWER(c.category) LIKE LOWER(CONCAT('%', :search, '%'))
                ORDER BY c.category
            """)
    List<CategoryListResponse> searchCategoriesByCategory(@Param("search") String search);
}
