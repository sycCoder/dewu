package org.example;

import org.example.dao.OrderDao;
import org.example.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest
public class OrderServiceApplicationTests {
    @Autowired
    OrderDao orderDao;

    @Test
    public void test1() {
//        Order order = new Order();
//        order.setOrderState(0);
//        order.setCreateTime(new Date());
//        order.setEndTime(new Date());
//        order.setItemSaleId(1);
//        order.setUserId(1);
//        order.setOrderId(1);
//        Boolean state = orderDao.createOrder(order);
//        System.out.println(state);
//        System.out.println(order.getOrderId());
    }

    @Test
    public void test2() {
        Boolean i = orderDao.updateOrderState(1, 10);
        System.out.println(i);
    }
}
