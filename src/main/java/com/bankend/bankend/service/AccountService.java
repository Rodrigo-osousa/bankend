package com.bankend.bankend.service;

import com.bankend.bankend.entity.model.Account;
import com.bankend.bankend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Account updateAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Iterable<Account> obtainAccount() {
        return accountRepository.findAll();
    }

    public Optional<Account> obtainAccountId(int id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}
