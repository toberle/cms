package com.example.cms.controller;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.menuitem.MenuItemCreateUpdateDto;
import com.example.cms.api.rest.menuitem.MenuItemDto;
import com.example.cms.service.MenuItemService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Timed
@RestController
@RequestMapping("/menu-items")
public class MenuItemRestController {

    private final MenuItemService menuItemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDto<MenuItemDto>> getAll() {
        return ResponseEntity.ok(menuItemService.getAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuItemService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemDto> create(@RequestBody MenuItemCreateUpdateDto create) {
        return ResponseEntity.ok(menuItemService.create(create));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemDto> update(@PathVariable("id") Long id, @RequestBody MenuItemCreateUpdateDto update) {
        return ResponseEntity.ok(menuItemService.update(id, update));
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuItemService.delete(id));
    }
}
