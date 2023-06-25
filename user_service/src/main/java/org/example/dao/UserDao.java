package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.User;

@Mapper
public interface UserDao {
    @Select("select * from dewu.user where user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_status", property = "userStatus"),
            @Result(column = "id_card", property = "idCard")
    })
    User findUserByUserId(Integer userId);

    @Select("select * from dewu.user where phone = #{phone}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_status", property = "userStatus"),
            @Result(column = "id_card", property = "idCard")
    })
    User findUserByPhone(String phone);

    @Insert("insert into dewu.user(phone, nickname, user_status, certification) values (#{phone}, #{nickname}, 1, false)")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void addUser(String phone, String nickname);

    @Update("update dewu.user set nickname = #{nickname} where user_id = #{userId}")
    Boolean changeNickname(String nickname, Integer userId);

    @Update("update dewu.user set avatar = #{avatar} where user_id = #{userId}")
    Boolean changeAva(String avatar, Integer userId);
}
