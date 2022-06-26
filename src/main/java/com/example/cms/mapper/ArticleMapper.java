package com.example.cms.mapper;

import com.example.cms.api.rest.article.ArticleCreateUpdateDto;
import com.example.cms.api.rest.article.ArticleDto;
import com.example.cms.domain.Article;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto map(Article article);
    List<ArticleDto> map(List<Article> article);

    Article map(ArticleCreateUpdateDto dto);

    void update(ArticleCreateUpdateDto dto, @MappingTarget Article article);
}
