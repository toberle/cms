package com.example.cms.service;

import com.example.cms.api.rest.ListDto;
import com.example.cms.api.rest.staticpage.StaticPageCreateUpdateDto;
import com.example.cms.api.rest.staticpage.StaticPageDto;

public interface StaticPageService {

    ListDto<StaticPageDto> getAll(int pageNumber);
    StaticPageDto getById(Long id);
    StaticPageDto getByAlias(String alias);
    StaticPageDto create(StaticPageCreateUpdateDto dto);
    StaticPageDto update(Long id, StaticPageCreateUpdateDto dto);
    StaticPageDto delete(Long id);
}
