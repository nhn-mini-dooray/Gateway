package com.nhnacademy.mini_dooray.gateway.auth.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GitEmailDto {
    private final String email;
    private final Boolean primary;
    private final Boolean verified;
    private final String visibility;
}
