package com.my.springmybatis.section01.factorybean;

import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //필드 전체 초기화용 생성자 추가
public class MenuService {
  private final SqlSessionTemplate sqlSession;

  //Autowired, bean 등록된 객체중에 필요한 타입 찾아서 의존성 주입
//  public MenuService(SqlSessionTemplate sqlSession){
//    this.sqlSession = sqlSession;
//  }

  public List<MenuDTO> findAllMenuByOrderableStatus(String orderableStatus){
    List<MenuDTO> menus = sqlSession.getMapper(MenuMapper.class).findAllMenuByOrderableStatus(orderableStatus);

    if(menus != null){
      // 셀렉트 성공 시
      menus.forEach(menu -> {
        if("Y".equals(menu.getOrderableStatus())){
          menu.setMenuName(menu.getMenuName() + "(주문가능)");
        }else{
          menu.setMenuName(menu.getMenuName() + "(주문불가능)");
        }
      });
    }
      return menus;
  }
}
