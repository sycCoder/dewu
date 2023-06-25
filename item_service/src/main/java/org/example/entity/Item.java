package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Result;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Item {
    Integer itemId;
    String itemName;
    Integer lowestPrice;
    String brand;
    Boolean storeItem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean itemAuditState;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer applyUserId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String coverImage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String cubeUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer infoClass;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ItemImage> images;

    public Map<String, Object> toMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(this));
        }
        return map;
    }
}
