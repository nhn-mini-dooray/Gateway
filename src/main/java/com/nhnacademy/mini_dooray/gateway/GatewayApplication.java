package com.nhnacademy.mini_dooray.gateway;

import com.nhnacademy.mini_dooray.gateway.properties.AccountProperties;
import com.nhnacademy.mini_dooray.gateway.properties.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RedisProperties.class, AccountProperties.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
