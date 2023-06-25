package org.example.service.impl;

import com.alibaba.fastjson2.JSONObject;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String getSmsCode(String phone) {
        return "1234";
    }

    @Override
    public JSONObject login(JSONObject request) {
        String code = request.getString("code");
        String phone = request.getString("phone");
        String nickname = "用户" + System.currentTimeMillis();
        JSONObject response = new JSONObject();
        if (Objects.equals(code, "1234")){
            User user = userDao.findUserByPhone(phone);
            if (user == null) {
                userDao.addUser(phone, nickname);
            }
            response.put("firstLogin", user == null);
            response.put("msg", "登录成功");
            response.put("state", true);
        } else {
            response.put("msg", "验证码错误");
            response.put("state", false);
        }
        return response;
    }

    @Override
    public User findUserByUserId(Integer userId) {
        return userDao.findUserByUserId(userId);
    }

    @Override
    public JSONObject changeAva(JSONObject request) {
        JSONObject response = new JSONObject();
        String avatar = request.getString("avatar");
        Integer userId = request.getInteger("userId");
        if (userDao.changeAva(avatar, userId)) {
            response.put("state", true);
        } else {
            response.put("state", false);
        }
        return response;
    }

    @Override
    public JSONObject changeNickname(JSONObject request) {
        JSONObject response = new JSONObject();
        String nickname = request.getString("nickname");
        Integer userId = request.getInteger("userId");
        if (userDao.changeNickname(nickname, userId)) {
            response.put("state", true);
        } else {
            response.put("state", false);
        }
        return response;
    }
}
