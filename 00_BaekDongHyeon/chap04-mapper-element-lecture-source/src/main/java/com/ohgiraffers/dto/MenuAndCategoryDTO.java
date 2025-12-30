package com.ohgiraffers.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuAndCategoryDTO {
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private String orderableStatus;

    private CategoryDTO category;
}