package com.nhnacademy.mini_dooray.gateway.domain.account.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    private final String loginId;

    private final String email;

    @Setter
    private String password;
}
