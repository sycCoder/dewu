package org.example.controller;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.example.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService = new OrderServiceImpl();

    @GetMapping("/list/{userId}")
    public List<Order> getAllOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getAllOrdersByUserId(userId);
    }

    @GetMapping("/list/{userId}/{receptionOrderState}")
    public JSONObject getOrdersByUserIdAndReceptionOrderState(@PathVariable Integer userId, @PathVariable Integer receptionOrderState) {
        return orderService.getOrdersByUserIdAndReceptionOrderState(userId, receptionOrderState);
    }

    @GetMapping("/{orderId}")
    public Order getOrderByOrderId(@PathVariable Integer orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @GetMapping("/payment/{orderId}")
    public Integer getPaymentByOrderId(@PathVariable Integer orderId) {
        return orderService.getPaymentByOrderId(orderId);
    }

    @PostMapping
    public Object createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderId}/{orderState}")
    public Map<String, Object> updateOrderState(@PathVariable Integer orderId, @PathVariable Integer orderState) {
        return orderService.updateOrderState(orderId, orderState);
    }

    @DeleteMapping("/{orderId}")
    public Map<String, Object> cancelOrder(@PathVariable Integer orderId) {
        return orderService.cancelOrder(orderId);
    }

    @PutMapping("/refund/{orderId}")
    public JSONObject createRefund(@PathVariable Integer orderId){
        return orderService.createRefund(orderId);
    }
}
