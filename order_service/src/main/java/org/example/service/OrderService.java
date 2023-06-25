package org.example.service;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrdersByUserId(Integer userId);

    JSONObject getOrdersByUserIdAndReceptionOrderState(Integer userId, Integer orderState);

    Order getOrderByOrderId(Integer orderId);

    Map<String, Object> createOrder(Order order);

    Map<String, Object> updateOrderState(Integer orderId, Integer orderState);

    Integer getPaymentByOrderId(Integer orderId);

    Map<String, Object> cancelOrder(Integer orderId);

    void checkOrders();

    JSONObject createRefund(Integer orderId);

    JSONObject refundAccessed(Integer orderId);
}
