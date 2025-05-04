package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.category.ArticleCategoryCreateUpdateDto;
import com.example.cms.api.rest.category.ArticleCategoryDto;
import com.example.cms.service.ArticleCategoryService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/categories")
public class ArticleCategoryRestController {

    private final ArticleCategoryService articleCategoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<ArticleCategoryDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(articleCategoryService.getAll(page));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleCategoryDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articleCategoryService.getById(id));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleCategoryDto> create(@RequestBody ArticleCategoryCreateUpdateDto create) {
        return ResponseEntity.ok(articleCategoryService.create(create));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleCategoryDto> update(@PathVariable("id") Long id, @RequestBody ArticleCategoryCreateUpdateDto update) {
        return ResponseEntity.ok(articleCategoryService.update(id, update));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleCategoryDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articleCategoryService.delete(id));
    }
}
