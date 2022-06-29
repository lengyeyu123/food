package com.han.food.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String openId;

    private String unionId;

    private String avatarUrl;

    private String nickName;

    private String remark;

    private Data createTime;

    private Data updateTime;

    private String available;

}
