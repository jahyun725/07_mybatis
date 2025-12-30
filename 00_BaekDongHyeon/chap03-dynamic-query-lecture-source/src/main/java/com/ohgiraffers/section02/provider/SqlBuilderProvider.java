package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * SQL 클래스를 사용하여 INSERT, UPDATE, DELETE 쿼리를 생성하는 Provider 클래스
 * Mybatis의 Java API를 통해 DML 문자열을 프로그래밍 방식으로 작성
 */
public class SqlBuilderProvider {

    /**
     * 메뉴 등록 SQL 생성
     * SQL 클래스를 사용하여 INSERT 구문을 메서드 체이닝 방식으로 작성
     * @param menuDTO 등록할 메뉴 정보
     * @return String 생성된 INSERT SQL 문자열
     */
    public String insertMenu(MenuDTO menuDTO){
        return new SQL()
                .INSERT_INTO("tbl_menu")
                .VALUES("menu_name", "#{ menuName }")
                .VALUES("menu_price", "#{ menuPrice }")
                .VALUES("category_code", "#{ categoryCode }")
                .VALUES("orderable_status", "#{ orderableStatus }")
                .toString();
    }

    /**
     * 메뉴 수정 동적 SQL 생성
     * 전달된 MenuDTO의 각 필드 값이 유효한 경우에만 SET 절에 추가하는 동적 쿼리
     * - menuName: null이 아니고 빈 문자열이 아닌 경우
     * - orderableStatus: null이 아니고 빈 문자열이 아닌 경우
     * - categoryCode: 0보다 큰 경우
     * - menuPrice: 0보다 큰 경우
     * @param menuDTO 수정할 메뉴 정보 (유효한 필드만 UPDATE 대상)
     * @return String 생성된 동적 UPDATE SQL 문자열
     */
    public String updateMenu(MenuDTO menuDTO) {
        SQL sql = new SQL();
        sql.UPDATE("tbl_menu");

        if(menuDTO.getMenuName() != null & !menuDTO.getMenuName().isEmpty()) {
            sql.SET("menu_name = #{ menuName }");
        }

        if(menuDTO.getOrderableStatus() != null & !menuDTO.getOrderableStatus().isEmpty()) {
            sql.SET("orderable_status = #{ orderableStatus }");
        }

        if(menuDTO.getCategoryCode() > 0) {
            sql.SET("category_code = #{ categoryCode }");
        }

        if(menuDTO.getMenuPrice() > 0) {
            sql.SET("menu_price = #{ menuPrice }");
        }

        sql.WHERE("menu_code = #{ menuCode }");

        return sql.toString();
    }

    /**
     * 메뉴 삭제 SQL 생성
     * SQL 클래스를 사용하여 DELETE 구문을 메서드 체이닝 방식으로 작성
     * @Param으로 전달된 menuCode를 사용하기 때문에 메서드 매개변수는 없어야 함
     * @return String 생성된 DELETE SQL 문자열
     */
    public String deleteMenu() {
        return new SQL()
                .DELETE_FROM("tbl_menu")
                .WHERE("menu_code = #{ menuCode }")
                .toString();
    }

}
