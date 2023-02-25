package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.category.CategoryCreateUpdateDto;
import com.example.cms.api.rest.category.CategoryDto;
import com.example.cms.service.CategoryService;
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
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<CategoryDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(categoryService.getAll(page));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryCreateUpdateDto create) {
        return ResponseEntity.ok(categoryService.create(create));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id, @RequestBody CategoryCreateUpdateDto update) {
        return ResponseEntity.ok(categoryService.update(id, update));
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }
}
