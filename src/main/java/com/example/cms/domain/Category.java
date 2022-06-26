package com.example.cms.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @Size(min = 1, max = 20)
    private String name;

    @Column(name = "alias", unique = true)
    @Size(min = 1, max = 20)
    private String alias;
}
