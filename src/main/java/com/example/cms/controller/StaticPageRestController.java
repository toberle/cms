package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.staticpage.StaticPageCreateUpdateDto;
import com.example.cms.api.rest.staticpage.StaticPageDto;
import com.example.cms.service.StaticPageService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/static-pages")
public class StaticPageRestController {

    private final StaticPageService staticPageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<StaticPageDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(staticPageService.getAll(page));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticPageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(staticPageService.getById(id));
    }

    @GetMapping(path = "/a/{alias}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticPageDto> getByAlias(@PathVariable String alias) {
        return ResponseEntity.ok(staticPageService.getByAlias(alias));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticPageDto> create(@RequestBody StaticPageCreateUpdateDto dto) {
        return ResponseEntity.ok(staticPageService.create(dto));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticPageDto> update(@RequestBody StaticPageCreateUpdateDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(staticPageService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticPageDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(staticPageService.delete(id));
    }
}
