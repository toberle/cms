package com.example.cms.mapper;

import com.example.cms.api.rest.menuitem.MenuItemCreateUpdateDto;
import com.example.cms.api.rest.menuitem.MenuItemDto;
import com.example.cms.domain.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemDto map(MenuItem menuItem);
    List<MenuItemDto> map(List<MenuItem> menuItems);

    MenuItem map(MenuItemCreateUpdateDto dto);

    void updateFromDto(MenuItemCreateUpdateDto dto, @MappingTarget MenuItem menuItem);
}
