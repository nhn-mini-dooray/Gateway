package com.nhnacademy.mini_dooray.gateway.config_properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
public class RedisConfigProperties {
    private String host;
    private int port;
    private String password;
    private int database;
}
