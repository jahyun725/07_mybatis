package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SelectBuilder를 사용하는 Mapper 인터페이스
 * @SelectProvider 어노테이션을 통해 Java 코드로 작성된 동적 SQL을 매핑
 */
public interface SelectBuilderMapper {
    /**
     * 전체 메뉴 조회
     * SelectBuilderProvider의 selectAllMenu 메서드를 통해 SQL 생성
     * @return List<MenuDTO> 주문 가능한 전체 메뉴 목록
     */
    @SelectProvider(type = SelectBuilderProvider.class, method = "selectAllMenu")
    List<MenuDTO> selectAllMenu();

    /**
     * 메뉴명 또는 카테고리명으로 메뉴 검색 (동적 SQL)
     * SelectBuilderProvider의 searchMenuByNameOrCategory 메서드를 통해 동적 SQL 생성
     * @param searchCriteria 검색 조건(name/category)과 검색어를 담은 객체
     * @return List<MenuDTO> 검색 조건에 맞는 메뉴 목록
     */
    @SelectProvider(type = SelectBuilderProvider.class, method = "searchMenuByNameOrCategory")
    List<MenuDTO> searchMenuByNameOrCategory(SearchCriteria searchCriteria);
}
