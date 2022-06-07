package com.spring.jpaapp.service;

import com.spring.jpaapp.model.Delivery;
import com.spring.jpaapp.model.Member;
import com.spring.jpaapp.model.Order;
import com.spring.jpaapp.model.OrderItem;
import com.spring.jpaapp.model.item.Item;
import com.spring.jpaapp.repository.ItemRepository;
import com.spring.jpaapp.repository.MemberRepository;
import com.spring.jpaapp.repository.OrderRepository;
import com.spring.jpaapp.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }


    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancle(); //엔티티의 값만 바꿔줘도 update쿼리가 날라간다.. 더티체킹..
    }

    //검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
