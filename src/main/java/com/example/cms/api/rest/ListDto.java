package com.example.cms.api.rest;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ListDto<T> {

    private List<T> data;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
}
