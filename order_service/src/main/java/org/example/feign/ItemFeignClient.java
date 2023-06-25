package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@FeignClient(name = "item-service")
public interface ItemFeignClient {
    @GetMapping("/item/sale/{itemSaleId}")
    Map<String, Object> getItemSaleById(@PathVariable Integer itemSaleId);

    @PutMapping("/item/processing/{itemSaleId}")
    Boolean processingItemSale(@PathVariable Integer itemSaleId);

    @PutMapping("/item/return/{itemSaleId}")
    Boolean returnItemSale(@PathVariable Integer itemSaleId);
}
