package com.my.transaction.service;

import com.my.transaction.dto.OrderDTO;
import com.my.transaction.dto.OrderMenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceIntegrationTest {

  @Autowired
  private OrderService orderService;

  // Spring Framework에서 제공하는 클래스로 JDBC를 이용한 데이터베이스 접근을 쉽게 해주는 도구
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testRegisterOrder() {
    // given
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setOrderDate("20251229");
    orderDTO.setOrderTime("100430");
    orderDTO.setTotalOrderPrice(50000);

    OrderMenuDTO menuDTO1 = new OrderMenuDTO();
    menuDTO1.setMenuCode(1);
    menuDTO1.setOrderAmount(2);

    OrderMenuDTO menuDTO2 = new OrderMenuDTO();
    menuDTO2.setMenuCode(2);
    menuDTO2.setOrderAmount(3);

    List<OrderMenuDTO> orderMenuDTOS = Arrays.asList(menuDTO1, menuDTO2);

    // when
    orderService.registerOrder(orderDTO, orderMenuDTOS);

    // then
    Integer orderCount = jdbcTemplate.queryForObject(
        "SELECT COUNT(*) FROM tbl_order", Integer.class
    );

    Integer orderMenuCount = jdbcTemplate.queryForObject(
        "SELECT COUNT(*) FROM tbl_order_menu", Integer.class
    );

    assertThat(orderCount).isEqualTo(1);
    assertThat(orderMenuCount).isEqualTo(2);

    String orderDate = jdbcTemplate.queryForObject(
        "SELECT order_date FROM tbl_order", String.class
    );
    assertThat(orderDate).isEqualTo("20251229");

  }

}