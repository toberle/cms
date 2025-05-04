package com.example.cms.mapper;

import com.example.cms.api.rest.category.ArticleCategoryDto;
import com.example.cms.api.rest.category.ArticleCategoryCreateUpdateDto;
import com.example.cms.domain.ArticleCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleCategoryMapper {

    ArticleCategoryDto map(ArticleCategory category);
    List<ArticleCategoryDto> map(List<ArticleCategory> categories);

    ArticleCategory map(ArticleCategoryCreateUpdateDto dto);

    void updateFromDto(ArticleCategoryCreateUpdateDto dto, @MappingTarget ArticleCategory category);
}
