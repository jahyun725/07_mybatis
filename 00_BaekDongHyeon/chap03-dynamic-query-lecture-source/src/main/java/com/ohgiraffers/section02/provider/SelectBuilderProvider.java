package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.jdbc.SQL;

/**
 * SQL 클래스를 사용하여 동적 SELECT 쿼리를 생성하는 Provider 클래스
 * Mybatis의 Java API를 통해 SQL 문자열을 프로그래밍 방식으로 작성
 */
public class SelectBuilderProvider {

    /**
     * 전체 메뉴 조회 SQL 생성
     * SQL 클래스를 사용하여 SELECT 구문을 메서드 체이닝 방식으로 작성
     * @return String 생성된 SQL 문자열
     */
    public String selectAllMenu() {
        return new SQL()
                .SELECT("menu_code")
                .SELECT("menu_name")
                .SELECT("menu_price")
                .SELECT("category_code")
                .SELECT("orderable_status")
                .FROM("tbl_menu")
                .WHERE("orderable_status = 'Y'")
                .toString();
    }

    /**
     * 검색 조건에 따른 동적 SQL 생성
     * 검색 조건(condition)이 'category'인 경우: 카테고리명으로 검색 (JOIN 사용)
     * 검색 조건(condition)이 'name'인 경우: 메뉴명으로 LIKE 검색
     * @param searchCriteria 검색 조건과 검색어
     * @return String 생성된 동적 SQL 문자열
     */
    public String searchMenuByNameOrCategory(SearchCriteria searchCriteria) {
        SQL sql = new SQL();
        sql.SELECT(
            "menu_code",
                "menu_name",
                "menu_price",
                "category_code",
                "orderable_status")
                .FROM("tbl_menu");

        if("category".equals(searchCriteria.getCondition())) {
            sql.JOIN("tbl_category USING (category_code)")
                    .WHERE("orderable_status = 'Y'")
                    .AND()
                    .WHERE("category_name = #{ value }");
        }

        if ("name".equals(searchCriteria.getCondition())) {
            // where 메소드에 가변인자로 전달하는 경우 자동적으로 AND 조건 처리 되므로
            // OR의 경우 별도 메소드 사용
            sql.WHERE(
                    "orderable_status = 'Y'",
                    "menu_name LIKE CONCAT('%', #{ value }, '%')"
            );
        }

        return sql.toString();
    }






}
