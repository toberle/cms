package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.article.ArticleCreateUpdateDto;
import com.example.cms.api.rest.article.ArticleDto;
import com.example.cms.service.ArticleService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<ArticleDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(articleService.getAll(page));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleCreateUpdateDto create) {
        return ResponseEntity.ok(articleService.create(create));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> update(@PathVariable("id") Long id, @RequestBody ArticleCreateUpdateDto update) {
        return ResponseEntity.ok(articleService.update(id, update));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articleService.delete(id));
    }
}
