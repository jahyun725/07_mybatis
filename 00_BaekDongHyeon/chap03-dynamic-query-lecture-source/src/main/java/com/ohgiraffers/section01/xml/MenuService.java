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

    sqlSession.close();

  }

  public void searchMenu(SearchCriteria searchCriteria) {

    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    List<MenuDTO> menuList = dynamicSqlMapper.searchMenu(searchCriteria);

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색 결과 ===");
      menuList.forEach(System.out::println);

    } else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }

    sqlSession.close();

  }

  public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    List<MenuDTO> menuList = dynamicSqlMapper.searchMenuBySupCategory(searchCriteria);

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색 결과 ===");
      menuList.forEach(System.out::println);

    } else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }

    sqlSession.close();

  }

  public void searchMenuByRandomMenuCode(Set<Integer> randomMenuCodeList) {

    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);


    Map<String, Set<Integer>> criteria = new HashMap<>();
    criteria.put("randomMenuCodeList", randomMenuCodeList);

    List<MenuDTO> menuList
        = dynamicSqlMapper.searchMenuByRandomMenuCode(criteria);

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색 결과 ===");
      menuList.forEach(System.out::println);

    } else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }

    sqlSession.close();

  }

  public void searchMenuByNameOrCategory(Map<String, Object> criteria) {

    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    List<MenuDTO> menuList
        = dynamicSqlMapper.searchMenuByNameOrCategory(criteria);

    if(menuList != null && !menuList.isEmpty()){
      System.out.println("=== 검색 결과 ===");
      menuList.forEach(System.out::println);

    } else{
      System.out.println("### 검색 결과가 없습니다. ###");
    }

    sqlSession.close();
  }



  public void modifyMenu(Map<String, Object> criteria) {

    SqlSession sqlSession = getSqlSession();
    DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

    int result = dynamicSqlMapper.updateMenu(criteria);

    if(result > 0){
      System.out.println("=== 수정 성공 ===");
      sqlSession.commit();
    } else{
      System.out.println("### 수정 실패... ###");
      sqlSession.rollback();
    }

    sqlSession.close();

  }
}
