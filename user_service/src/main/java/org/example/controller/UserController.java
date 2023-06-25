package org.example.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shaoyichao
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/vCode/{phone}")
    public String getSmsCode(@PathVariable String phone){
        return userService.getSmsCode(phone);
    }

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject request) {
        return userService.login(request);
    }

    @PutMapping("/ava")
    public JSONObject changeAva(@RequestBody JSONObject request) {
        return userService.changeAva(request);
    }

    @PutMapping("/nickname")
    public JSONObject changeNickname(@RequestBody JSONObject request) {
        return userService.changeNickname(request);
    }
}
