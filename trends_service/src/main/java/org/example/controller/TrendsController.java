package org.example.controller;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Trends;
import org.example.service.TrendsService;
import org.example.service.impl.TrendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trends")
public class TrendsController {
    @Autowired
    TrendsService trendsService = new TrendsServiceImpl();

    @GetMapping("/{userId}")
    List<Trends> getTrendsByUserId(@PathVariable Integer userId) {
        return trendsService.getTrendsByUserId(userId);
    }

    @GetMapping("/one/{trendsId}")
    Trends getTrendsById(@PathVariable Integer trendsId) {
        return trendsService.getTrendsById(trendsId);
    }

    @GetMapping
    List<Trends> getAllTrends(){
        return trendsService.getAllTrends();
    }

    @DeleteMapping("/one/{trendsId}")
    JSONObject deleteTrendsByTrendsId(@PathVariable Integer trendsId) {
        return trendsService.deleteTrendsByTrendsId(trendsId);
    }

    @PostMapping
    JSONObject addTrends(@RequestBody JSONObject request) {
        return trendsService.addTrends(request);
    }
}
