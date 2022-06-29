package com.han.food.framework.config;

import com.han.food.common.constant.FoodConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FoodWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private FoodConfig foodConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String uploadFilePathPrefix = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("win")) {
            uploadFilePathPrefix = foodConfig.getUploadFilePathWinPrefix();
        } else {
            uploadFilePathPrefix = foodConfig.getUploadFilePathLinuxPrefix();
        }
        registry.addResourceHandler(FoodConstants.FILE_VISIT_PREFIX + "**").addResourceLocations("file:" + uploadFilePathPrefix);
    }


}
