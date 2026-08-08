package com.faca_receita.category.mappers;

import com.faca_receita.category.dtos.CategoryResponse;
import com.faca_receita.category.dtos.CategorySaveRequest;
import com.faca_receita.category.models.Category;
import com.faca_receita.configs.MapperConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface CategoryMapper {

    @Mapping(target = "active", ignore = true)
    Category toEntity(CategorySaveRequest request);

    CategoryResponse toResponse(Category category);

}

