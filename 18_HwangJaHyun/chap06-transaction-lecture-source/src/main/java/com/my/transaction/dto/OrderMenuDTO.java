package com.my.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDTO {
  private int menuCode;
  private int orderAmount;
}