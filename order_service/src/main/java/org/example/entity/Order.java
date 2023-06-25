package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer orderId;
    Integer itemSaleId;
    Integer userId;
    Date createTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date endTime;
    Integer orderState;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer payment;
    String recipient;
    String recipientPhone;
    String address;
}
