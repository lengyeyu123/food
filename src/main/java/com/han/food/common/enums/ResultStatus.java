package com.han.food.common.enums;

import lombok.Getter;

@Getter
public enum ResultStatus {

    OK(0, "成功"),

    ERROR(50000, "服务器内部错误"),

    INVALID_TOKEN(-1, "请登陆后重试"),

    ACCOUNT_ERROR(50101, "账户异常"),

    VIOLATION_CONTENT(50102, "内容不安全"),

    ERROR_USER_NAME_OR_PASSWORD(50103, "用户名密码错误");


    // 用户模块错误码 50100~50199
    // 商品模块错误码 50200~50299
    // 订单模块错误码 50300~50399

    private final Integer code;
    private final String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
