package com.my.section01.xml;

import com.my.common.MenuDTO;
import com.my.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.my.common.Template.getSqlSession;

public class MenuService {

  public void selectMenuByPrice(int price) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);
    // mybatis가 mapper를 구현한 객체가 반환됨

    Map<String, Integer> map = new HashMap<>();
    map.put("price", price);

    // SQL 수행 후 결과 반환받기
    List<MenuDTO> menuList = dynamicSqlMapper.selectMenuByPrice(map);
    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색결과 ===");
      menuList.forEach(System.out::println);
    }else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }
    sqlSession.close();
  }

  public void searchMenu(SearchCriteria searchCriteria) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);
    List<MenuDTO> menuList = dynamicSqlMapper.searchMenu(searchCriteria);
    // dynamicSqlMapper 인터페이스인데 호출 가능한 이유 : 프록시객체를 만들어서... 마이바티스가 그걸 해줌

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색결과 ===");
      menuList.forEach(System.out::println);
    }else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }
    sqlSession.close();

  }

  public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);
    List<MenuDTO> menuList = dynamicSqlMapper.searchMenuBySupCategory(searchCriteria);
    // dynamicSqlMapper 인터페이스인데 호출 가능한 이유 : 프록시객체를 만들어서... 마이바티스가 그걸 해줌

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색결과 ===");
      menuList.forEach(System.out::println);
    }else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }
    sqlSession.close();

  }

  public void searchMenuByRandomMenuCode(Set<Integer> randomMenuCodeList) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    Map<String, Set<Integer>> criteria = new HashMap<>();
    criteria.put("randomMenuCodeList", randomMenuCodeList);

    List<MenuDTO> menuList = dynamicSqlMapper.searchMenuByRandomMenuCode(criteria);
    // dynamicSqlMapper 인터페이스인데 호출 가능한 이유 : 프록시객체를 만들어서... 마이바티스가 그걸 해줌

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색결과 ===");
      menuList.forEach(System.out::println);
    }else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }
    sqlSession.close();
  }

  public void searchMenuByNameOrCategory(Map<String, Object> criteria) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    List<MenuDTO> menuList = dynamicSqlMapper.searchMenuByNameOrCategory(criteria);
    // dynamicSqlMapper 인터페이스인데 호출 가능한 이유 : 프록시객체를 만들어서... 마이바티스가 그걸 해줌

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색결과 ===");
      menuList.forEach(System.out::println);
    }else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }
    sqlSession.close();
  }

  public void modifyMenu(Map<String, Object> criteria) {
    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    int result = dynamicSqlMapper.updateMenu(criteria);
    // dynamicSqlMapper 인터페이스인데 호출 가능한 이유 : 프록시객체를 만들어서... 마이바티스가 그걸 해줌

    if(result > 0){
      System.out.println("=== 수정되었습니다. ===");
      sqlSession.commit();
    }else{
      System.out.println("### 수정 실패 ###");
      sqlSession.rollback();
    }
    sqlSession.close();
  }
}
