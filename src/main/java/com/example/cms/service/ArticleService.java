package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.article.ArticleCreateUpdateDto;
import com.example.cms.api.rest.article.ArticleDto;

public interface ArticleService {
    ListDto<ArticleDto> getAll(int page);
    ListDto<ArticleDto> getAllByCategoryAlias(int page, String categoryAlias);
    ArticleDto getById(Long id);
    ArticleDto create(ArticleCreateUpdateDto dto);
    ArticleDto update(Long id, ArticleCreateUpdateDto dto);
    ArticleDto delete(Long id);
}
