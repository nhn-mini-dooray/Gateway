package com.nhnacademy.mini_dooray.gateway.domain.account.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    private final String loginId;

    private final String email;

    @Setter
    private String password;
}
