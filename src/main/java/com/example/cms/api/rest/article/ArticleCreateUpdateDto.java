package com.example.cms.api.rest.article;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ArticleCreateUpdateDto {
    private Long id;
    private String title;
    private String alias;
    private String content;
    private boolean published;
    private LocalDateTime publishedAt;

    private Long categoryId;
}
