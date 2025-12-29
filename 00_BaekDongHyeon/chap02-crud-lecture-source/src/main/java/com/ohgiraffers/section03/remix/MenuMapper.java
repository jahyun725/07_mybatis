package com.ohgiraffers.section03.remix;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MenuMapper {

  List<MenuDTO> selectAllMenu();

  MenuDTO selectMenuByMenuCode(int menuCode);

  int insertMenu(MenuDTO menu);

  int updateMenu(MenuDTO menu);

  int deleteMenu(int menuCode);

}
