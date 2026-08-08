package com.faca_receita.category.controller;

import com.faca_receita.category.dtos.CategoryListResponse;
import com.faca_receita.category.dtos.CategoryResponse;
import com.faca_receita.category.dtos.CategorySaveRequest;
import com.faca_receita.category.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategorySaveRequest category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.create(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryListResponse>> search(@RequestParam String search) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.search(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }
}
