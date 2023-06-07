package com.nhnacademy.mini_dooray.gateway.domain.account.controller;

import com.nhnacademy.mini_dooray.gateway.auth.service.ApiUserDetailsService;
import com.nhnacademy.mini_dooray.gateway.domain.account.model.Account;
import com.nhnacademy.mini_dooray.gateway.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final ApiUserDetailsService apiUserDetailsService;

    @PostMapping("signUp")
    public String signUp(Account account) {
        String encodePassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodePassword);
        accountService.signUp(account);
        UserDetails userDetails = apiUserDetailsService.loadUserByUsername(account.getLoginId());
        apiUserDetailsService.forceLogin(userDetails);
        return "redirect:/";
    }
}
