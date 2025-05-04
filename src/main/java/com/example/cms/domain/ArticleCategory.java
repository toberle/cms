package com.example.cms.domain;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "article_categories")
public class ArticleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @Size(min = 1, max = 20)
    private String name;

    @Column(name = "alias", unique = true)
    @Size(min = 1, max = 20)
    private String alias;
}
