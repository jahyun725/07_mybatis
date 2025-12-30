package com.ohgiraffers.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
  private String orderDate;
  private String orderTime;
  private int totalOrderPrice;
}