package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    @Size(min = 1, max = 100)
    private String title;

    @Column(name = "alias", unique = true)
    @Size(min = 1, max = 100)
    private String alias;

    @Column(name = "published")
    @Convert(converter = BooleanConverter.class)
    private boolean published;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ArticleCategory category;
}
