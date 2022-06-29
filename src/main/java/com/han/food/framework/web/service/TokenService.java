package com.han.food.framework.web.service;

import com.han.food.common.constant.FoodConstants;
import com.han.food.common.utils.JwtUtils;
import com.han.food.framework.config.properties.TokenProperties;
import com.han.food.framework.web.domain.LoginUser;
import com.han.food.project.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;

    /**
     * 验证jwt
     *
     * @param jwt       jwt字符串
     * @param tokenType token类型
     * @return 通过验证返回true
     */
    public boolean verifyToken(String jwt, String tokenType) {
        TokenProperties.TokenConfig tokenConfig = tokenType.equals(FoodConstants.ACCESS_TOKEN_TYPE) ? tokenProperties.getAtConfig() : tokenProperties.getRtConfig();
        try {
            JwtUtils.parseJwt(jwt, tokenConfig);
            return true;
        } catch (Exception e) {
            log.error("token解析异常", e);
            return false;
        }
    }

    public String getOpenId(String token) {
        return (String) JwtUtils.parseJwt(token, tokenProperties.getAtConfig()).get("openId");
    }

    public Integer getUserId(String token) {
        return (Integer) JwtUtils.parseJwt(token, tokenProperties.getAtConfig()).get("id");
    }

    public LoginUser getLoginUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        String accessToken = JwtUtils.generateJwt(tokenProperties.getAtConfig(), claims);
        String refreshToken = JwtUtils.generateJwt(tokenProperties.getRtConfig(), claims);
        return new LoginUser().setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setUser(user);
    }
}
