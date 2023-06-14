package com.nhnacademy.mini_dooray.gateway.domain.account.model.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResponseDto {
    private final String loginId;

    private final Long accountId;

    private final String password;
}
