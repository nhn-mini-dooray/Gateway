package com.nhnacademy.mini_dooray.gateway.domain.account.repository;

import com.nhnacademy.mini_dooray.gateway.domain.account.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryRepository {
    private static final Map<String, Account> ACCOUNTS = new HashMap<>();

    public void signUp(Account account) {
        ACCOUNTS.put(account.getLoginId(), account);
    }

    public Optional<Account> login(String username) {
        return Optional.ofNullable(ACCOUNTS.getOrDefault(username, null));
    }
}
