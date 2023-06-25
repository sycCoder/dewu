package org.example.service;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.User;

public interface UserService {
    String getSmsCode(String phone);

    JSONObject login(JSONObject request);


    User findUserByUserId(Integer userId);

    JSONObject changeAva(JSONObject request);

    JSONObject changeNickname(JSONObject request);
}
