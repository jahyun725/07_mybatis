package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * SqlBuilder를 사용하는 Mapper 인터페이스
 * @InsertProvider, @UpdateProvider, @DeleteProvider 어노테이션을 통해
 * Java 코드로 작성된 INSERT, UPDATE, DELETE 동적 SQL을 매핑
 */
public interface SqlBuilderMapper {

    /**
     * 신규 메뉴 등록
     * SqlBuilderProvider의 insertMenu 메서드를 통해 INSERT SQL 생성
     * @param menuDTO 등록할 메뉴 정보
     * @return int 등록된 행의 개수
     */
    @InsertProvider(type = SqlBuilderProvider.class, method = "insertMenu")
    int insertMenu(MenuDTO menuDTO);

    /**
     * 메뉴 정보 수정 (동적 SQL)
     * 전달된 파라미터가 유효한 값(문자열: 빈 문자열 아님, 숫자: 0 초과)을 가질 경우에만
     * 해당 컬럼을 수정에 반영하는 동적 쿼리로 구현
     * SqlBuilderProvider의 updateMenu 메서드를 통해 동적 UPDATE SQL 생성
     * @param menuDTO 수정할 메뉴 정보 (유효한 값만 반영됨)
     * @return int 수정된 행의 개수
     */
    @UpdateProvider(type = SqlBuilderProvider.class, method = "updateMenu")
    int updateMenu(MenuDTO menuDTO);

    /**
     * 메뉴 삭제
     * SqlBuilderProvider의 deleteMenu 메서드를 통해 DELETE SQL 생성
     * 기본 자료형 값을 전달하는 경우 @Param 어노테이션을 사용해야 함
     * (전달 값이 2개 이상인 경우에도 @Param 어노테이션 필요)
     * 단, Provider 메서드의 매개변수 선언부는 없어야 함
     * @param menuCode 삭제할 메뉴의 코드
     * @return int 삭제된 행의 개수
     */
    @DeleteProvider(type = SqlBuilderProvider.class, method = "deleteMenu")
    int deleteMenu(@Param("menuCode") int menuCode);



}
