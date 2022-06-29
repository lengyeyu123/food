package com.han.food.project.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class FormLoginReqVo {

    // form 登录方式 用户名 密码
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}
