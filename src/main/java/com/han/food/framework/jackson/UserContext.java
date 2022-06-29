package com.han.food.framework.jackson;

import com.han.food.common.enums.ResultStatus;
import com.han.food.common.exception.ServiceException;
import com.han.food.project.domain.User;

public class UserContext {

    private static final ThreadLocal<User> loginUser = new ThreadLocal<>();

    public static Integer getUserId() {
        return getUser().getId();
    }


    public static User getUser() {
        User user = loginUser.get();
        if (user == null) {
            throw new ServiceException(ResultStatus.INVALID_TOKEN);
        }
        return user;
    }

    public static void setUser(User user) {
        loginUser.set(user);
    }

    public static void remove() {
        loginUser.remove();
    }

}
