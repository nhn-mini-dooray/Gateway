package com.nhnacademy.mini_dooray.gateway.auth.service;

import com.nhnacademy.mini_dooray.gateway.auth.model.GatewayUser;
import com.nhnacademy.mini_dooray.gateway.domain.account.model.Account;
import com.nhnacademy.mini_dooray.gateway.domain.account.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
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
    private final MemoryRepository memoryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = memoryRepository.login(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new GatewayUser(account.getLoginId(), account.getPassword());
    }

    public void forceLogin(UserDetails userDetails) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
