package com.ohgiraffers.springmybatis.section01.factorybean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MenuDTO {
  private int menuCode;
  private String menuName;
  private int menuPrice;
  private int categoryCode;
  private String orderableStatus;
}
