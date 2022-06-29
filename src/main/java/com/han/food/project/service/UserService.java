package com.han.food.project.service;

import com.han.food.project.domain.User;
import com.han.food.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }
}
