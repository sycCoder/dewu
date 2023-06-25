package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Address {
    String county;
    String address;
    Integer addressId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer userId;
    Boolean addressDefault;
}
