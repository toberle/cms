package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    private Long id;

    @Column(name = "name", unique = true)
    @Size(min = 1, max = 20)
    private String name;

    @Column(name = "url")
    @Size(min = 1, max = 100)
    private String url;

    @Column(name = "active")
    @Convert(converter = BooleanConverter.class)
    private boolean active;

    @Column(name = "priority")
    private int priority;
}
