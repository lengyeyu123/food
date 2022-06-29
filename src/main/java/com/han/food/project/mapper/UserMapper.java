package com.han.food.project.mapper;

import com.han.food.project.domain.User;

public interface UserMapper {

    User selectById(Integer id);

    User selectByUserName(String userName);
}
