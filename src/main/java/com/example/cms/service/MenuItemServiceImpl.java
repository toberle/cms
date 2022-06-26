package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.menuitem.MenuItemCreateUpdateDto;
import com.example.cms.api.rest.menuitem.MenuItemDto;
import com.example.cms.domain.MenuItem;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.MenuItemMapper;
import com.example.cms.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemMapper mapper;
    private final MenuItemRepository repository;

    @Transactional(readOnly = true)
    @Override
    public ListDto<MenuItemDto> getAll() {
        List<MenuItem> menuItems = repository.findAll();
        ListDto<MenuItemDto> dto = new ListDto<>();
        dto.setData(mapper.map(menuItems));
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public MenuItemDto getById(Long id) {
        MenuItem menuItem = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found - id:" + id));
        return mapper.map(menuItem);
    }

    @Transactional
    @Override
    public MenuItemDto create(MenuItemCreateUpdateDto dto) {
        MenuItem menuItem = mapper.map(dto);
        MenuItem saved = repository.save(menuItem);
        return mapper.map(saved);
    }

    @Transactional
    @Override
    public MenuItemDto update(Long id, MenuItemCreateUpdateDto dto) {
        MenuItem menuItem = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found - cannot update - id:" + id));
        mapper.updateFromDto(dto, menuItem);
        MenuItem updated = repository.save(menuItem);
        return mapper.map(updated);
    }

    @Transactional
    @Override
    public MenuItemDto delete(Long id) {
        MenuItem menuItem = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found - cannot update - id:" + id));
        repository.deleteById(id);
        return mapper.map(menuItem);
    }
}
