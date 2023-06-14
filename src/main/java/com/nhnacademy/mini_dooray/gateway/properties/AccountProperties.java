package com.nhnacademy.mini_dooray.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "url.account")
@Getter
@Setter
public class AccountProperties {
    private String origin;
    private String signup;
    private String login;
    private String email;
    private String withdrawal;
    private String dormant;
}
