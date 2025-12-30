package com.ohgiraffers.springmybatis.section01.factorybean;

import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@AllArgsConstructor // 필드 전체 초기화용 생성자 추가
public class MenuService {

  private final SqlSessionTemplate sqlSession;

//  public MenuService(SqlSessionTemplate sqlSession){
//    this.sqlSession = sqlSession;
//  }


  public List<MenuDTO>
    findAllMenuByOrderableStatus(String orderableStatus){

    List<MenuDTO> menus
        = sqlSession
        .getMapper(MenuMapper.class)
        .findAllMenuByOrderableStatus(orderableStatus);

    if(menus != null){ // select 성공 시
      menus.forEach(menu -> {
        if("Y".equals(menu.getOrderableStatus())){
          menu.setMenuName(menu.getMenuName() + "(주문 가능)");
        }else{
          menu.setMenuName(menu.getMenuName() + "(주문 불가능)");
        }
      });

    }

    return menus;
  }

}
