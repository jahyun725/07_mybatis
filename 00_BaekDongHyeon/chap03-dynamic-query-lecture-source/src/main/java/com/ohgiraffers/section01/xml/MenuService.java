package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {

  public void selectMenuByPrice(int price) {
    SqlSession sqlSession = getSqlSession();

    DynamicSqlMapper dynamicSqlMapper
        = sqlSession.getMapper(DynamicSqlMapper.class);

    Map<String, Integer> map = new HashMap<>();
    map.put("price", price);

    // SQL 수행 후 결과 반환
    List<MenuDTO> menuList = dynamicSqlMapper.selectMenuByPrice(map);

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색 결과 ===");
      menuList.forEach(System.out::println);

    } else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }

  }

  public void searchMenu(SearchCriteria searchCriteria) {
  }

  public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
  }

  public void searchMenuByRandomMenuCode(Set<Integer> randomMenuCodeList) {
  }

  public void searchMenuByNameOrCategory(Map<String, Object> stringObjectMap) {
  }

  public void modifyMenu(Map<String, Object> stringObjectMap) {
  }
}
