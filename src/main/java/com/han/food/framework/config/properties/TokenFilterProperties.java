package com.han.food.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties("filter.ignore")
public class TokenFilterProperties {

    private List<String> whites = new ArrayList<>();

}
