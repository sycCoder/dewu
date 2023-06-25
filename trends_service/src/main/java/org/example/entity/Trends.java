package org.example.entity;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class Trends {
    Integer trendsId;
    String title;
    String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer likes;
    Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<JSONObject> items;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> images;
}
