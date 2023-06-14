package com.nhnacademy.mini_dooray.gateway.auth.service;

import com.nhnacademy.mini_dooray.gateway.auth.model.EmailLoginRequestDto;
import com.nhnacademy.mini_dooray.gateway.auth.model.GatewayUser;
import com.nhnacademy.mini_dooray.gateway.auth.model.GitEmailDto;
import com.nhnacademy.mini_dooray.gateway.domain.account.model.response.AccountResponseDto;
import com.nhnacademy.mini_dooray.gateway.properties.AccountProperties;
import com.nhnacademy.mini_dooray.gateway.util.RequestApiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class OAuth2GithubUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private static final String GIT_API_REQUEST_URL = "https://api.github.com/user/emails";
    private static final String GIT_API_ACCEPT_HEADER = "application/vnd.github+json";
    private static final String GIT_API_VERSION_HEADER = "X-GitHub-Api-Version";
    private static final String GIT_API_VERSION_VALUE = "2022-11-28";

    private final RequestApiHelper requestApiHelper;
    private final AccountProperties accountProperties;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        HttpHeaders headers = setHeader(userRequest.getAccessToken());

        List<GitEmailDto> response = requestApiHelper.sendRequest(
                GIT_API_REQUEST_URL, HttpMethod.GET, headers,
                new ParameterizedTypeReference<>() {
                });

        String email = findPrimaryEmail(response);

        AccountResponseDto accountResponseDto =
                requestApiHelper.sendRequest(accountProperties.getOrigin() + accountProperties.getEmail(),
                        HttpMethod.POST, new EmailLoginRequestDto(email), new ParameterizedTypeReference<>() {
                        });
        return new GatewayUser(accountResponseDto.getLoginId(), null, accountResponseDto.getAccountId());
    }

    private HttpHeaders setHeader(OAuth2AccessToken accessToken) {
        HttpHeaders headers = requestApiHelper.getDefaultHeaders();
        headers.setAccept(List.of(MediaType.valueOf(GIT_API_ACCEPT_HEADER)));
        headers.setBearerAuth(accessToken.getTokenValue());
        headers.set(GIT_API_VERSION_HEADER, GIT_API_VERSION_VALUE);
        return headers;
    }

    private static String findPrimaryEmail(List<GitEmailDto> response) {
        return response.stream()
                .filter(GitEmailDto::getPrimary)
                .findAny()
                .orElseThrow(IllegalAccessError::new)
                .getEmail();
    }
}
