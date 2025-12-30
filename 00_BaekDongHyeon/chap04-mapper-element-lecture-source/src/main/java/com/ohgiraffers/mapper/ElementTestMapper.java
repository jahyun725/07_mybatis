package com.ohgiraffers.mapper;

import com.ohgiraffers.dto.CategoryAndMenuDTO;
import com.ohgiraffers.dto.MenuAndCategoryDTO;
import com.ohgiraffers.dto.MenuDTO;

import java.util.List;

public interface ElementTestMapper {
  List<MenuDTO> selectResultMapTest();

  List<MenuAndCategoryDTO> selectResultMapAssociationTest();

  List<CategoryAndMenuDTO> selectResultMapCollectionTest();
}
