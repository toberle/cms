package com.example.cms.service;

import com.example.cms.api.rest.category.CategoryCreateUpdateDto;
import com.example.cms.api.rest.category.CategoryDto;
import com.example.cms.api.rest.ListDto;
import com.example.cms.domain.Category;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.CategoryMapper;
import com.example.cms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final int PAGE_SIZE = 20;
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public ListDto<CategoryDto> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Category> categoryPage = repository.findAll(pageRequest);
        List<CategoryDto> categoryDtoList = mapper.map(categoryPage.getContent());
        return ListDto.<CategoryDto>builder()
                .data(categoryDtoList)
                .page(categoryPage.getNumber())
                .size(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - id" + id));
        return mapper.map(category);
    }

    @Transactional
    @Override
    public CategoryDto create(CategoryCreateUpdateDto dto) {
        Category category = mapper.map(dto);
        Category created = repository.save(category);
        return mapper.map(created);
    }

    @Transactional
    @Override
    public CategoryDto update(Long id, CategoryCreateUpdateDto dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - cannot update - id" + id));
        mapper.updateFromDto(dto, category);
        Category updated = repository.save(category);
        return mapper.map(updated);
    }

    @Transactional
    @Override
    public CategoryDto delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found - cannot delete - id" + id));
        repository.deleteById(id);
        return mapper.map(category);
    }
}
