package com.example.cms.service;

import com.example.cms.api.rest.category.ArticleCategoryCreateUpdateDto;
import com.example.cms.api.rest.category.ArticleCategoryDto;
import com.example.cms.api.rest.ListDto;

public interface ArticleCategoryService {
    ListDto<ArticleCategoryDto> getAll(int page);
    ArticleCategoryDto getById(Long id);
    ArticleCategoryDto create(ArticleCategoryCreateUpdateDto dto);
    ArticleCategoryDto update(Long id, ArticleCategoryCreateUpdateDto dto);
    ArticleCategoryDto delete(Long id);
}
