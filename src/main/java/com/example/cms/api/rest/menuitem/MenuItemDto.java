package com.example.cms.api.rest.menuitem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuItemDto {

    private Long id;
    private String name;
    private String url;
    private boolean active;
    private int priority;
}
