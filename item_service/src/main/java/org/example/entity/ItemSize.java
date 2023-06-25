package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ItemSize {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer itemSizeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer itemId;
    String size;
}
