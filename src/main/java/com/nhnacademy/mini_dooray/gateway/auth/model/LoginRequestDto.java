package com.nhnacademy.mini_dooray.gateway.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private final String loginId;
}
