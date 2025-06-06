package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
