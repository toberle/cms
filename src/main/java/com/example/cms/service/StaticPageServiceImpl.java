package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.staticpage.StaticPageCreateUpdateDto;
import com.example.cms.api.rest.staticpage.StaticPageDto;
import com.example.cms.domain.StaticPage;
import com.example.cms.exception.EntityNotFoundException;
import com.example.cms.mapper.StaticPageMapper;
import com.example.cms.repository.StaticPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StaticPageServiceImpl implements StaticPageService {

    private final StaticPageRepository staticPageRepository;
    private final StaticPageMapper staticPageMapper;

    @Transactional(readOnly = true)
    @Override
    public ListDto<StaticPageDto> getAll(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 20);
        Page<StaticPage> page = staticPageRepository.findAll(pageRequest);
        List<StaticPageDto> dtoList = staticPageMapper.map(page.getContent());
        return new ListDto<>(dtoList, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public StaticPageDto getById(Long id) {
        StaticPage staticPage = staticPageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Static page with ID '%s' not found", id)));
        return staticPageMapper.map(staticPage);
    }

    @Transactional(readOnly = true)
    @Override
    public StaticPageDto getByAlias(String alias) {
        StaticPage staticPage = staticPageRepository.findByAlias(alias)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Static page with alias '%s' not found", alias)));
        return staticPageMapper.map(staticPage);
    }

    @Transactional
    @Override
    public StaticPageDto create(StaticPageCreateUpdateDto dto) {
        StaticPage staticPage = staticPageMapper.map(dto);
        StaticPage saved = staticPageRepository.save(staticPage);
        return staticPageMapper.map(saved);
    }

    @Transactional
    @Override
    public StaticPageDto update(Long id, StaticPageCreateUpdateDto dto) {
        StaticPage staticPage = staticPageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String
                        .format("Static page with ID '%d' not found - update failed", id)));
        staticPageMapper.update(dto, staticPage);
        return staticPageMapper.map(staticPage);
    }

    @Transactional
    @Override
    public StaticPageDto delete(Long id) {
        StaticPage staticPage = staticPageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String
                        .format("Static page with ID '%d' not found - delete failed", id)));
        staticPageRepository.deleteById(id);
        return staticPageMapper.map(staticPage);
    }
}
