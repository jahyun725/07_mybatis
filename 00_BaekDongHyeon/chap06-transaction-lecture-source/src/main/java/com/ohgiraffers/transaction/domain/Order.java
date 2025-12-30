package com.ohgiraffers.transaction.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Order {
    private int orderCode;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;
}