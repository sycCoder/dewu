package org.example.controller;

import org.example.entity.Pay;
import org.example.service.PayService;
import org.example.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    PayService payService = new PayServiceImpl();

    @PostMapping
    public Map<String, Object> createPayment(@RequestBody Pay pay) {
        return payService.createPayment(pay);
    }

    @PutMapping("/{payId}")
    public Map<String, Object> pay(@PathVariable Integer payId) {
        return payService.pay(payId);
    }
}
