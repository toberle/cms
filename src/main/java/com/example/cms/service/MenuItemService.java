package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.menuitem.MenuItemCreateUpdateDto;
import com.example.cms.api.rest.menuitem.MenuItemDto;

public interface MenuItemService {
    ListDto<MenuItemDto> getAll();
    MenuItemDto getById(Long id);
    MenuItemDto create(MenuItemCreateUpdateDto dto);
    MenuItemDto update(Long id, MenuItemCreateUpdateDto dto);
    MenuItemDto delete(Long id);
}
