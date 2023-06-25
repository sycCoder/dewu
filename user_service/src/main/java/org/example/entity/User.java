package org.example.entity;

import lombok.Data;

@Data
public class User {
    String avatar;
    String userId;
    String nickname;
    String phone;
    Integer userStatus;
    String name;
    /**
     * 实名认证
     */
    String idCard;
    Boolean certification;
}
