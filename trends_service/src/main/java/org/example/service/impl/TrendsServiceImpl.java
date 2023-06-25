package org.example.service.impl;

import com.alibaba.fastjson2.JSONObject;
import org.example.dao.TrendsDao;
import org.example.entity.Trends;
import org.example.feign.ItemFeignClient;
import org.example.service.TrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrendsServiceImpl implements TrendsService {
    @Autowired
    TrendsDao trendsDao;
    @Autowired
    ItemFeignClient itemFeignClient;

    @Override
    public List<Trends> getTrendsByUserId(Integer userId) {
        List<Trends> trendsList = trendsDao.getTrendsByUserId(userId);
        for (Trends t: trendsList) {
            List<Integer> itemIds = trendsDao.getTrendsItemIds(t.getTrendsId());
            List<JSONObject> items = new ArrayList<>();
            for (Integer itemId: itemIds) {
                JSONObject item = JSONObject.from(itemFeignClient.getItemById(itemId));
                item.remove("images");
                item.remove("cubeUrl");
                item.remove("info");
                items.add(item);
            }
            t.setItems(items);
        }
        return trendsList;
    }

    @Override
    public Trends getTrendsById(Integer trendsId) {
        Trends trends = trendsDao.getTrendsById(trendsId);
        List<Integer> itemIds = trendsDao.getTrendsItemIds(trendsId);
        List<JSONObject> items = new ArrayList<>();
        for (Integer itemId: itemIds) {
            JSONObject item = JSONObject.from(itemFeignClient.getItemById(itemId));
            item.remove("images");
            item.remove("cubeUrl");
            item.remove("info");
            items.add(item);
        }
        trends.setItems(items);
        return trends;
    }

    @Override
    public List<Trends> getAllTrends() {
        List<Trends> trendsList = trendsDao.getAllTrends();
        for (Trends t: trendsList) {
            List<Integer> itemIds = trendsDao.getTrendsItemIds(t.getTrendsId());
            List<JSONObject> items = new ArrayList<>();
            for (Integer itemId: itemIds) {
                JSONObject item = JSONObject.from(itemFeignClient.getItemById(itemId));
                item.remove("images");
                item.remove("cubeUrl");
                item.remove("info");
                items.add(item);
            }
            t.setItems(items);
        }
        return trendsList;
    }

    @Override
    public JSONObject deleteTrendsByTrendsId(Integer trendsId) {
        JSONObject response = new JSONObject();
        response.put("state", trendsDao.deleteTrendsByTrendsId(trendsId));
        return response;
    }

    @Override
    public JSONObject addTrends(JSONObject request) {
        JSONObject response = new JSONObject();
        Trends trends = new Trends();
        trends.setDescription(request.getString("description"));
        trends.setTitle(request.getString("title"));
        trends.setUserId(request.getInteger("userId"));
        System.out.println(trends);
        try {
            trendsDao.addTrends(trends);
            Integer trendsId = trends.getTrendsId();
            for (String image: request.getJSONArray("images").toList(String.class)) {
                System.out.println(image);
                trendsDao.addTrendsImage(image, trendsId);
            }
            for (Integer itemId: request.getJSONArray("items").toList(Integer.class)) {
                System.out.println(itemId);
                trendsDao.addTrendsItem(trendsId, itemId);
            }
            response.put("state", true);
            response.put("trendsId", trendsId);
        } catch (Exception e) {
            response.put("state", false);
        }
        return response;
    }
}
