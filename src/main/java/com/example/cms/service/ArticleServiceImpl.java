package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.article.ArticleCreateUpdateDto;
import com.example.cms.api.rest.article.ArticleDto;
import com.example.cms.domain.Article;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.ArticleMapper;
import com.example.cms.repository.ArticleRepository;
import com.example.cms.repository.ArticleCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    public static final int PAGE_SIZE = 20;
    private final ArticleRepository articleRepository;
    private final ArticleCategoryRepository articleCategoryRepository;
    private final ArticleMapper articleMapper;

    @Transactional(readOnly = true)
    @Override
    public ListDto<ArticleDto> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Article> articlePage = articleRepository.findAll(pageRequest);
        return ListDto.<ArticleDto>builder()
                .data(articleMapper.map(articlePage.getContent()))
                .page(articlePage.getNumber())
                .size(articlePage.getSize())
                .totalPages(articlePage.getTotalPages())
                .totalElements(articlePage.getTotalElements())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ListDto<ArticleDto> getAllByCategoryAlias(int page, String categoryAlias) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Article> articlePage = articleRepository.findAllByCategoryAlias(categoryAlias, pageRequest);
        return ListDto.<ArticleDto>builder()
                .data(articleMapper.map(articlePage.getContent()))
                .page(articlePage.getNumber())
                .totalPages(articlePage.getTotalPages())
                .size(articlePage.getSize())
                .totalPages(articlePage.getTotalPages())
                .totalElements(articlePage.getTotalElements())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found - id: " +id));
        return articleMapper.map(article);
    }

    @Transactional
    @Override
    public ArticleDto create(ArticleCreateUpdateDto dto) {
        Article article = articleMapper.map(dto);
        if (dto.getCategoryId() != null) {
            article.setCategory(articleCategoryRepository.getById(dto.getCategoryId()));
        }
        Article created = articleRepository.save(article);
        return articleMapper.map(created);
    }

    @Transactional
    @Override
    public ArticleDto update(Long id, ArticleCreateUpdateDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found - cannot update - id: " + id));
        articleMapper.update(dto, article);
        if (dto.getCategoryId() != null) {
            article.setCategory(articleCategoryRepository.getById(dto.getCategoryId()));
        }
        Article updated = articleRepository.save(article);
        return articleMapper.map(updated);
    }

    @Transactional
    @Override
    public ArticleDto delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found - cannot delete - id: "+  id));
        articleRepository.deleteById(id);
        return articleMapper.map(article);
    }
}
