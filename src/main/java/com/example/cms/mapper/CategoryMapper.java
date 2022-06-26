package com.example.cms.mapper;

import com.example.cms.api.rest.category.CategoryCreateUpdateDto;
import com.example.cms.api.rest.category.CategoryDto;
import com.example.cms.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto map(Category category);
    List<CategoryDto> map(List<Category> categories);

    Category map(CategoryCreateUpdateDto dto);

    void updateFromDto(CategoryCreateUpdateDto dto, @MappingTarget Category category);
}
