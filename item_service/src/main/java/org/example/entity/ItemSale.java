package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ItemSale {
    Integer itemSaleId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer itemId;
    Integer itemSizeId;
    Integer price;
    Integer itemSaleState;
}
