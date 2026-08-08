package com.faca_receita.category.services;

import com.faca_receita.category.dtos.CategoryListResponse;
import com.faca_receita.category.dtos.CategoryResponse;
import com.faca_receita.category.dtos.CategorySaveRequest;
import com.faca_receita.category.mappers.CategoryMapper;
import com.faca_receita.category.models.Category;
import com.faca_receita.category.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse create(CategorySaveRequest categorySaveRequest) {
        Category category = this.categoryMapper.toEntity(categorySaveRequest);
        category.setActive(true);
        return this.categoryMapper.toResponse(this.categoryRepository.save(category));
    }

    public List<CategoryListResponse> search(String search) {
        return this.categoryRepository.searchCategoriesByCategory(search);
    }

    public CategoryResponse findById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return categoryMapper.toResponse(category);
    }
}
