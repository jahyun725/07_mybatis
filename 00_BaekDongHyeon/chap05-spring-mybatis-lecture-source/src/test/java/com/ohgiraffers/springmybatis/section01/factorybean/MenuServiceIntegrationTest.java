package com.ohgiraffers.springmybatis.section01.factorybean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* 통합 테스트
 * 실제 스프링 컨텍스트를 로딩하여 MenuService의 전체 동작(실제 DB와 연동 및 비즈니스 로직 적용)
 * 을 검증하는 테스트 */
@SpringBootTest // 스프링 부트의 전체 컨텍스트를 로딩
@ActiveProfiles("test") // 테스트 실행 시 application-test.yml 설정을 사용
@Transactional  // 테스트 완료 후 DB에 반영 된 변경 사항을 롤백
@Sql(statements = {
// 테스트용 데이터 삽입
    "INSERT INTO tbl_menu(menu_name, menu_price, category_code, orderable_status) " +
        "VALUES ('김치찌개', 8000, 1, 'Y')",
    "INSERT INTO tbl_menu(menu_name, menu_price, category_code, orderable_status) " +
        "VALUES ('된장찌개', 9000, 2, 'Y')",
    "INSERT INTO tbl_menu(menu_name, menu_price, category_code, orderable_status) " +
        "VALUES ('비빔밥', 10000, 3, 'N')"
})
public class MenuServiceIntegrationTest {

  @Autowired // 의존성 주입(DI)
  private MenuService menuService;


  /* MenuService에서 제공하는 findAllMenuByOrderableStatus 기능 중
   * 주문 가능 상태가 'Y'인 메뉴들을 조회하는 테스트 메소드
   * 주문 가능한 메뉴들이 반환되며 메뉴 이름에 (주문 가능)이 표시되는지 검증 */
  @Test
  public void testFindAllMenuByOrderableStatus_Orderable() {
    // given
    String orderableStatus = "Y";

    // when
    List<MenuDTO> resultList = menuService.findAllMenuByOrderableStatus(orderableStatus);

    // then
    resultList.forEach(System.out::println);

    // 일관 된 db 상태를 통해 수행 된 행의 개수 등을 반환받는 테스트가 가능
    assertNotNull(resultList);
    assertEquals(2, resultList.size());

    resultList.forEach(menu ->
        assertTrue(menu.getMenuName().endsWith("(주문 가능)"),
            "주문 가능 메뉴의 이름은 '(주문 가능)'으로 끝나야 합니다.")
    );

  }

  /* MenuService에서 제공하는 findAllMenuByOrderableStatus 기능 중
   * 주문 가능 상태가 'N'인 메뉴들을 조회하는 테스트 메소드
   * 주문 불가능한 메뉴들이 반환되며 메뉴 이름에 (주문 불가능)이 표시되는지 검증 */
  @Test
  public void testFindAllMenuByOrderableStatus_NotOrderable() {
    // given
    String orderableStatus = "N";

    // when
    List<MenuDTO> resultList = menuService.findAllMenuByOrderableStatus(orderableStatus);

    // then
    resultList.forEach(System.out::println);

    // 일관 된 db 상태를 통해 수행 된 행의 개수 등을 반환받는 테스트가 가능
    assertNotNull(resultList);
    assertEquals(1, resultList.size());

    resultList.forEach(menu ->
        assertTrue(menu.getMenuName().endsWith("(주문 불가능)"),
            "주문 가능 메뉴의 이름은 '(주문 불가능)'으로 끝나야 합니다.")
    );

  }
}
