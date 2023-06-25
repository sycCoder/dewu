package org.example.service;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Trends;

import java.util.List;

public interface TrendsService {
    List<Trends> getTrendsByUserId(Integer userId);

    Trends getTrendsById(Integer trendsId);

    List<Trends> getAllTrends();

    JSONObject deleteTrendsByTrendsId(Integer trendsId);

    JSONObject addTrends(JSONObject request);
}
