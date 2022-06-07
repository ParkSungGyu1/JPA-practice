package com.spring.jpaapp.repository;


import com.spring.jpaapp.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus; //주문상태[ORDER, CANCEL]
}
