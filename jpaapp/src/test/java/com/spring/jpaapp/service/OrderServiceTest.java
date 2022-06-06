package com.spring.jpaapp.service;

import com.spring.jpaapp.exception.NotEnoughStockException;
import com.spring.jpaapp.model.Address;
import com.spring.jpaapp.model.Member;
import com.spring.jpaapp.model.Order;
import com.spring.jpaapp.model.OrderStatus;
import com.spring.jpaapp.model.item.Book;
import com.spring.jpaapp.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "1234-1234"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000*orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }
    @Test()
    public void 상품주문_재고_수량초과() throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 주문취소() throws Exception{
        //given

        //when

        //then
    }
    @Test
    public void 상품주문_재고수량_초과() throws Exception{
        //given

        //when

        //then
    }
}