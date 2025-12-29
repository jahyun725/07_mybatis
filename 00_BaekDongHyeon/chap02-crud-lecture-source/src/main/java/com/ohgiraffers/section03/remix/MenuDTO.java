package com.ohgiraffers.section03.remix;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuDTO {
  private int menuCode;
  private String menuName;
  private int menuPrice;
  private int categoryCode;
  private String orderableStatus;
}
