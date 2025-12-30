package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

/**
 * SelectBuilder 기능을 테스트하는 Service 클래스
 * 기본 SELECT 구문과 동적 SQL 구문을 실행하여 결과를 출력
 */
public class SelectBuilderService {
    /**
     * 기본 SELECT 구문 테스트
     * 전체 메뉴 목록을 조회하여 출력
     */
    public void testSimpleStatement() {
        SqlSession sqlSession = getSqlSession();
        SelectBuilderMapper mapper = sqlSession.getMapper(SelectBuilderMapper.class);

        List<MenuDTO> menuList = mapper.selectAllMenu();

        if(menuList != null && !menuList.isEmpty()) {
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    /**
     * 동적 SQL 구문 테스트
     * 검색 조건에 따라 동적으로 생성된 SQL로 메뉴를 검색하여 출력
     * @param searchCriteria 검색 조건(name/category)과 검색어
     */
    public void testDynamicStatement(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        SelectBuilderMapper mapper = sqlSession.getMapper(SelectBuilderMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(searchCriteria);

        if(menuList != null && !menuList.isEmpty()) {
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }
}
