package com.nhnacademy.mini_dooray.gateway.auth.service;

import com.nhnacademy.mini_dooray.gateway.auth.model.GatewayUser;
import com.nhnacademy.mini_dooray.gateway.auth.model.LoginRequestDto;
import com.nhnacademy.mini_dooray.gateway.domain.account.model.response.AccountResponseDto;
import com.nhnacademy.mini_dooray.gateway.properties.AccountProperties;
import com.nhnacademy.mini_dooray.gateway.util.RequestApiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserDetailsService implements UserDetailsService {
    private final RequestApiHelper requestApiHelper;
    private final AccountProperties accountProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountResponseDto accountResponseDto =
                requestApiHelper.sendRequest(accountProperties.getOrigin() + accountProperties.getLogin(),
                        HttpMethod.POST, new LoginRequestDto(username), new ParameterizedTypeReference<>() {
                        });

        return new GatewayUser(accountResponseDto.getLoginId(), accountResponseDto.getPassword(),
                accountResponseDto.getAccountId());
    }

    public void forceLogin(UserDetails userDetails) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
