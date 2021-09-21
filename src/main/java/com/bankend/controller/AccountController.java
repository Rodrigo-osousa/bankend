package com.bankend.controller;


import com.bankend.model.entity.Account;
import com.bankend.model.entity.request.AccountRequest;
import com.bankend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/new")
    @ResponseBody
    public AccountRequest createAccount(@Valid @RequestBody AccountRequest accountRequest) throws Exception {
        return accountService.createAccount(accountRequest);

    }

    @GetMapping("/{id")
    public Optional<Account> obtainAccountID(@PathVariable int id){
        return accountService.obtainAccountId(id);
    }

    @PutMapping("/update")
    public Account updateAccount(@Valid Account account) {
        return accountService.updateAccount(account);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
    }

    @GetMapping("/list")
    public Iterable<Account> obtainAccount() {
        return accountService.obtainAccount();
    }

}
