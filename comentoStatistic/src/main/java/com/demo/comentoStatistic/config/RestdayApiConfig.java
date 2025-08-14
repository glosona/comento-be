package com.demo.comentoStatistic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "openapi.restday")
public class RestdayApiConfig {
    private String key;
    private String url;
}
