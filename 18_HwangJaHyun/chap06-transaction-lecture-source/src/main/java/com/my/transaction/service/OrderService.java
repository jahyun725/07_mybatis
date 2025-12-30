package com.my.transaction.service;

import com.my.transaction.domain.Order;
import com.my.transaction.domain.OrderMenu;
import com.my.transaction.dto.OrderDTO;
import com.my.transaction.dto.OrderMenuDTO;
import com.my.transaction.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor //모든 필드 초기화하는 생성자 코드
public class OrderService {
  private final OrderMapper orderMapper;

//  public OrderService(OrderMapper orderMapper) {
//    this.orderMapper = orderMapper;
//  }
  /**
   * 주문 등록 메서드
   * @param orderDTO
   * @param orderMenuDTOs
   */
  // 선언적 트랜잭션 처리 어노테이션
  @Transactional(
    isolation = Isolation.DEFAULT, // 하나의 트랜잭션으로 처리
    propagation = Propagation.REQUIRED, //트랜잭션이 있으면 참여, 없으면 생성
    rollbackFor = Exception.class // 발생한 예외 종류 관계 없이 모두 롤백
  )
  public void registerOrder(OrderDTO orderDTO, List<OrderMenuDTO> orderMenuDTOs){

    //DTO -> Domain 변환
    Order order = new Order();
    order.setOrderDate(orderDTO.getOrderDate());
    order.setOrderTime(orderDTO.getOrderTime());
    order.setTotalOrderPrice(orderDTO.getTotalOrderPrice());

    // tbl_order에 주문 정보 삽입(Domain을 전달)
    orderMapper.insertOrder(order);

    // tbl_order 테이블에 삽입된 order_code 번호 얻어오기
    int orderCode = order.getOrderCode();

    // DTO -> Domain 변환
    List<OrderMenu> orderMenus = orderMenuDTOs.stream().map(dto -> {
      OrderMenu om = new OrderMenu();
      om.setMenuCode(dto.getMenuCode());
      om.setOrderAmount(dto.getOrderAmount());
      // 주문 등록 후 auto generated 된 orderCode를 각 주문 메뉴에 설정
      om.setOrderCode(orderCode);
      return om; // return 값이 new Stream에 차곡차곡 쌓임
    }).collect(Collectors.toList());

    // 메뉴 정보 삽입
    orderMenus.forEach(orderMenu -> {
      orderMapper.insertOrderMenu(orderMenu);
    });
  }
}
