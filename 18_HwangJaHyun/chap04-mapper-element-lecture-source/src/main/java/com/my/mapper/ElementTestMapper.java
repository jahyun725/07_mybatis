package com.my.mapper;

import com.my.dto.CategoryAndMenuDTO;
import com.my.dto.MenuAndCategoryDTO;
import com.my.dto.MenuDTO;

import java.util.List;

public interface ElementTestMapper {

  List<MenuDTO> selectResultMapTest();

  List<MenuAndCategoryDTO> selectResultMapAssociationTest();

  List<CategoryAndMenuDTO> selectResultMapCollectionTest();
}
