package com.example.cms.service;

import com.example.cms.api.rest.category.ArticleCategoryDto;
import com.example.cms.api.rest.category.ArticleCategoryCreateUpdateDto;
import com.example.cms.api.rest.ListDto;
import com.example.cms.domain.ArticleCategory;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.ArticleCategoryMapper;
import com.example.cms.repository.ArticleCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
    private static final int PAGE_SIZE = 20;
    private final ArticleCategoryRepository repository;
    private final ArticleCategoryMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public ListDto<ArticleCategoryDto> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<ArticleCategory> categoryPage = repository.findAll(pageRequest);
        List<ArticleCategoryDto> articleCategoryDtoList = mapper.map(categoryPage.getContent());
        return ListDto.<ArticleCategoryDto>builder()
                .data(articleCategoryDtoList)
                .page(categoryPage.getNumber())
                .size(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleCategoryDto getById(Long id) {
        ArticleCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - id" + id));
        return mapper.map(category);
    }

    @Transactional
    @Override
    public ArticleCategoryDto create(ArticleCategoryCreateUpdateDto dto) {
        ArticleCategory category = mapper.map(dto);
        ArticleCategory created = repository.save(category);
        return mapper.map(created);
    }

    @Transactional
    @Override
    public ArticleCategoryDto update(Long id, ArticleCategoryCreateUpdateDto dto) {
        ArticleCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - cannot update - id" + id));
        mapper.updateFromDto(dto, category);
        ArticleCategory updated = repository.save(category);
        return mapper.map(updated);
    }

    @Transactional
    @Override
    public ArticleCategoryDto delete(Long id) {
        ArticleCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - cannot delete - id" + id));
        repository.deleteById(id);
        return mapper.map(category);
    }
}
