package com.han.food.framework.filter;

import com.han.food.common.constant.FoodConstants;
import com.han.food.common.enums.ResultStatus;
import com.han.food.common.utils.JsonUtils;
import com.han.food.common.utils.StringUtils;
import com.han.food.framework.config.properties.TokenFilterProperties;
import com.han.food.framework.jackson.UserContext;
import com.han.food.framework.web.domain.R;
import com.han.food.framework.web.service.TokenService;
import com.han.food.project.domain.User;
import com.han.food.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问站点的所有站点都进入此filter 1. 白名单请求可直接访问 2. 携带正确token可进行访问 3. 未携带token或错误token提醒未登录
 */
@Slf4j
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenFilterProperties tokenFilterProperties;

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if (StringUtils.matches(requestURI, tokenFilterProperties.getWhites())) {
            chain.doFilter(request, response);
        } else {
            String accessToken = httpRequest.getHeader(FoodConstants.ACCESS_TOKEN_TYPE);
            if (StringUtils.isNotBlank(accessToken)) {
                if (tokenService.verifyToken(accessToken, FoodConstants.ACCESS_TOKEN_TYPE)) {
                    Integer userId = tokenService.getUserId(accessToken);
                    if (userId == null || userId <= 0) {
                        return401(response);
                    } else {
                        User user = userService.selectById(userId);
                        if (user == null) {
                            return401(response);
                        } else {
                            UserContext.setUser(user);
                        }
                    }
                    chain.doFilter(request, response);
                } else {
                    return401(response);
                    ;
                }
            } else {
                return401(response);
            }
        }
    }

    private void return401(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write(JsonUtils.toJson(R.error(ResultStatus.INVALID_TOKEN)));
    }

    @Override
    public void destroy() {
        UserContext.remove();
    }
}
