package com.example.cms.service;

import com.example.cms.api.rest.category.CategoryCreateUpdateDto;
import com.example.cms.api.rest.category.CategoryDto;
import com.example.cms.api.rest.ListDto;

public interface CategoryService {
    ListDto<CategoryDto> getAll(int page);
    CategoryDto getById(Long id);
    CategoryDto create(CategoryCreateUpdateDto dto);
    CategoryDto update(Long id, CategoryCreateUpdateDto dto);
    CategoryDto delete(Long id);
}
