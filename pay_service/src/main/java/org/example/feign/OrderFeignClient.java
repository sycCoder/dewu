package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@FeignClient(name = "order-service")
public interface OrderFeignClient {
    @PutMapping("/order/{orderId}/{orderState}")
    Map<String, Object> updateOrderState(@PathVariable Integer orderId, @PathVariable Integer orderState);

    @GetMapping("/order/payment/{orderId}")
    Integer getPaymentByOrderId(@PathVariable Integer orderId);
}
