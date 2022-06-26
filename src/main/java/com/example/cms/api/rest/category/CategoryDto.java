package com.example.cms.api.rest.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {

    private Long id;
    private String alias;
    private String name;
}
