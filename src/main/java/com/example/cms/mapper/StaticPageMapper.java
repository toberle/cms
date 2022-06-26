package com.example.cms.mapper;

import com.example.cms.api.rest.staticpage.StaticPageCreateUpdateDto;
import com.example.cms.api.rest.staticpage.StaticPageDto;
import com.example.cms.domain.StaticPage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaticPageMapper {

    List<StaticPageDto> map(List<StaticPage> staticPages);
    StaticPageDto map(StaticPage staticPage);

    StaticPage map(StaticPageCreateUpdateDto createUpdateDto);

    void update(StaticPageCreateUpdateDto dto, @MappingTarget StaticPage staticPageToUpdate);
}
