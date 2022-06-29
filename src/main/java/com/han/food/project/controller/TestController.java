package com.han.food.project.controller;


import com.han.food.framework.jackson.UserContext;
import com.han.food.framework.web.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public R<?> test() {
        System.out.println(UserContext.getUser());
        return R.success();
    }


}
