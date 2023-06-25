package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "item-service")
public interface ItemFeignClient {
    @GetMapping("/item/{itemId}")
    Map<String, Object> getItemById(@PathVariable Integer itemId);
}