package com.nhnacademy.mini_dooray.gateway.domain.account.service;

import com.nhnacademy.mini_dooray.gateway.domain.account.model.Account;
import com.nhnacademy.mini_dooray.gateway.domain.account.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final MemoryRepository memoryRepository;
    public void signUp(Account account) {
        memoryRepository.signUp(account);
    }
}
