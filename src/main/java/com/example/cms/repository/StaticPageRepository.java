package com.example.cms.repository;

import com.example.cms.domain.StaticPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaticPageRepository extends JpaRepository<StaticPage, Long> {

    Optional<StaticPage> findByAlias(String alias);
}
