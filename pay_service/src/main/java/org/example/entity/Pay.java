package org.example.entity;

import lombok.Data;

@Data
public class Pay {
    Integer payId;
    Boolean state;
    String way;
    Integer payment;
    Integer orderId;
}
