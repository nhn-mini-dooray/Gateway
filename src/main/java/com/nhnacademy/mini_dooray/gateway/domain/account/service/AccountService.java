package com.nhnacademy.mini_dooray.gateway.domain.account.service;

import com.nhnacademy.mini_dooray.gateway.domain.account.model.request.SignUpRequestDto;
import com.nhnacademy.mini_dooray.gateway.properties.AccountProperties;
import com.nhnacademy.mini_dooray.gateway.util.RequestApiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final RequestApiHelper requestApiHelper;
    private final AccountProperties accountProperties;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        String encodePassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodePassword);

        requestApiHelper.sendRequest(
                accountProperties.getOrigin() + accountProperties.getSignup(),
                HttpMethod.POST,
                signUpRequestDto,
                new ParameterizedTypeReference<>() {
                });
    }
}
