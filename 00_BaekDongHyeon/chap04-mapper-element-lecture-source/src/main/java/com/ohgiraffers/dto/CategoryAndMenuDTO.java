package com.ohgiraffers.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryAndMenuDTO {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    private List<MenuDTO> menuList;
}