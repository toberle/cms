package com.example.cms.repository;

import com.example.cms.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a JOIN a.category c WHERE c.alias = :categoryAlias")
    Page<Article> findAllByCategoryAlias(String categoryAlias, Pageable pageable);
}
