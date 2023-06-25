package org.example.service;

import org.example.entity.Pay;

import java.util.Map;

public interface PayService {
    Map<String, Object> createPayment(Pay pay);

    Map<String, Object> pay(Integer payId);
}
