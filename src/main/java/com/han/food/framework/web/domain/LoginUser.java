package com.han.food.framework.web.domain;

import com.han.food.project.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginUser {

    private String accessToken;

    private String refreshToken;

    private User user;

}
