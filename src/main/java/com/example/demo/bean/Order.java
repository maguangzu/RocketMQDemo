package com.example.demo.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private String orderName;

    private Long orderId;

    private BigDecimal orderAmount;

    private Integer orderNum;

    private BigDecimal totalAmount;

    private String desc;
}
