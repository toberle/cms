package com.example.cms.domain;

import com.example.cms.domain.converter.BooleanConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "static_pages")
public class StaticPage {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    @Size(min = 1, max = 100)
    private String title;

    @Column(name = "alias", unique = true)
    @Size(min = 1, max = 100)
    private String alias;

    @Column(name = "content")
    @NotEmpty
    private String content;

    @Column(name = "published")
    @Convert(converter = BooleanConverter.class)
    private boolean published;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

}
