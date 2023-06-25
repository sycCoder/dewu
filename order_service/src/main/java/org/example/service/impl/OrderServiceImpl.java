package org.example.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.example.dao.OrderDao;
import org.example.entity.Order;
import org.example.feign.ItemFeignClient;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ItemFeignClient itemFeignClient;

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        return orderDao.getAllOrdersByUserId(userId);
    }

    @Override
    public JSONObject getOrdersByUserIdAndReceptionOrderState(Integer userId, Integer receptionOrderState) {
        JSONObject response = new JSONObject();
        int orderState;
        if (0 > receptionOrderState || 4 < receptionOrderState) {
            response.put("state", false);
            response.put("msg", "次订单状态不存在");
        } else {
            if (0 < receptionOrderState && receptionOrderState < 4) {
                orderState = receptionOrderState + 1;
                JSONArray orders = JSONArray.from(orderDao.getOrdersByUserIdAndOrderState(userId, orderState));
                response.put("orders", orders);
            } else if (receptionOrderState == 4) {
                JSONArray ordersOne = JSONArray.from(orderDao.getOrdersByUserIdAndOrderState(userId, 5));
                JSONArray ordersTwo = JSONArray.from(orderDao.getOrdersByUserIdAndOrderState(userId, 6));
                ordersOne.addAll(ordersTwo);
                response.put("orders", ordersOne);
            }
            response.put("state", true);
        }
        return response;
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        return orderDao.getOrderByOrderId(orderId);
    }

    @Override
    public Map<String, Object> createOrder(Order order) {
        Map<String, Object> message = new HashMap<>();
        try {
            Date now = new Date();
            order.setCreateTime(now);
            order.setOrderState(0);
            orderDao.createOrder(order);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            itemFeignClient.processingItemSale(order.getItemSaleId());
            String nowStr = dateFormat.format(now);
            message.put("state", true);
            message.put("createTime", nowStr);
            message.put("orderId", order.getOrderId());
            message.put("msg", "订单创建成功!");
            return message;
        } catch (Exception e) {
            message.put("state", false);
            message.put("msg", "订单创建失败,请重试!");
            return message;
        }
    }

    @Override
    public Map<String, Object> updateOrderState(Integer orderId, Integer orderState) {
        Map<String, Object> message = new HashMap<>();
        Boolean state = orderDao.updateOrderState(orderState, orderId);
        message.put("state", state);
        message.put("msg", state ? "订单状态修改成功" : "订单状态修改失败");
        return message;
    }

    @Override
    public Integer getPaymentByOrderId(Integer orderId) {
        return orderDao.getPaymentByOrderId(orderId);
    }

    @Override
    public Map<String, Object> cancelOrder(Integer orderId) {
        Map<String, Object> message = new HashMap<>();
        Boolean state = orderDao.cancelOrder(orderId);
        try {
            Order order = orderDao.getOrderByOrderId(orderId);
            itemFeignClient.returnItemSale(order.getItemSaleId());
        } catch (Exception e) {
            message.put("state", false);
            message.put("msg", "不存在该订单");
            return message;
        }
        message.put("state", state);
        message.put("msg", state ? "交易关闭!" : "交易关闭失败!");
        return message;
    }

    @Override
    public void checkOrders() {
        List<Order> unpaidOrders = orderDao.findAllUnpaidOrders();
        Date nowSubtract15 = new Date(System.currentTimeMillis() - 15 * 60 * 1000);
        for (Order order: unpaidOrders) {
            int timeState = nowSubtract15.compareTo(order.getCreateTime());
            if (timeState > 0) {
                orderDao.cancelOrder(order.getOrderId());
                itemFeignClient.returnItemSale(order.getItemSaleId());
            }
        }
    }

    @Override
    public JSONObject createRefund(Integer orderId) {
        Boolean state = orderDao.createRefund(orderId);
        JSONObject response = new JSONObject();
        response.put("state", state);
        return response;
    }

    @Override
    public JSONObject refundAccessed(Integer orderId) {
        Boolean state = orderDao.refundAccessed(orderId);
        JSONObject response = new JSONObject();
        response.put("state", state);
        return response;
    }
}
