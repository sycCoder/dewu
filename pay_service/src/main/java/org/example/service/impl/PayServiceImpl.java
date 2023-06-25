package org.example.service.impl;

import org.example.dao.PayDao;
import org.example.entity.Pay;
import org.example.feign.OrderFeignClient;
import org.example.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    PayDao payDao;
    @Autowired
    OrderFeignClient orderFeignClient;

    @Override
    public Map<String, Object> createPayment(Pay pay) {
        Map<String, Object> message = new HashMap<>();
        Boolean paymentState = payDao.getPaymentStateByOrderId(pay.getOrderId());
        if (paymentState) {
            message.put("state", false);
            message.put("msg", "已付款!");
            return message;
        }
        try {
            // 从order表中查询payment
            Integer payment = orderFeignClient.getPaymentByOrderId(pay.getOrderId());
            pay.setPayment(payment);
            payDao.createPayment(pay);
            message.put("state", true);
            message.put("msg", "付款创建成功!");
            return message;
        } catch (Exception e) {
            message.put("state", false);
            message.put("msg", "付款创建失败!");
            return message;
        }
    }

    @Override
    public Map<String, Object> pay(Integer payId){
        Map<String, Object> message = new HashMap<>();
        Boolean paymentState = payDao.getPaymentStateByPayId(payId);
        if (paymentState) {
            message.put("state", false);
            message.put("msg", "已付款!");
            return message;
        }
        Boolean state = payDao.pay(payId);
        if (state) {
            Integer orderId = payDao.getOrderIdByPayId(payId);
            orderFeignClient.updateOrderState(orderId, 2);
        }
        message.put("state", state);
        message.put("msg", state ? "付款成功!": "付款失败!");
        return message;
    }
}
