package org.example.component;

import org.example.service.OrderService;
import org.example.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTask {
    @Autowired
    OrderService orderService = new OrderServiceImpl();

    @Scheduled(cron = "0 0/15 * * * ?")
    public void cancelTimeoutOrders() {
        orderService.checkOrders();
    }
}
