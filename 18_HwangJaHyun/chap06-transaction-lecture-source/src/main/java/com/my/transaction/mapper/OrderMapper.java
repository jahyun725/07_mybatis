package com.my.transaction.mapper;


import com.my.transaction.domain.Order;
import com.my.transaction.domain.OrderMenu;
import org.apache.ibatis.annotations.Mapper;

// Mybatis가 해당 인터페이스를 구현하여
// 객체(proxy)로 만들고, 이를 bean으로 등록 (DAO에서 할일을 마이바티스가 proxy로)
@Mapper
public interface OrderMapper {
  void insertOrder(Order order);
  void insertOrderMenu(OrderMenu orderMenu);
}
