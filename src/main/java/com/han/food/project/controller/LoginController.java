package com.han.food.project.controller;

import com.han.food.common.constant.FoodConstants;
import com.han.food.common.enums.ResultStatus;
import com.han.food.framework.web.domain.R;
import com.han.food.framework.web.service.TokenService;
import com.han.food.project.domain.User;
import com.han.food.project.service.UserService;
import com.han.food.project.vo.FormLoginReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/formLogin")
    public R<?> formLogin(@RequestBody @Validated FormLoginReqVo reqVo) {
        User user = userService.selectByUserName(reqVo.getUserName());
        if (user == null) {
            return R.error(ResultStatus.ERROR_USER_NAME_OR_PASSWORD);
        }
        if (!user.getPassword().equalsIgnoreCase(reqVo.getPassword())) {
            return R.error(ResultStatus.ERROR_USER_NAME_OR_PASSWORD);
        }
        if (user.getAvailable().equals(FoodConstants.USER_STATUS_DISABLED)) {
            return R.error(ResultStatus.ACCOUNT_ERROR);
        }
        return R.success(tokenService.getLoginUser(user));
    }


}
