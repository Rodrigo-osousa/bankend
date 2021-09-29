package com.bankend.model.entity.controllers;


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

    @GetMapping("/{id}")
    public Optional<Account> searchAccountById(@PathVariable int id){
        return accountService.searchAccountById(id);
    }


    @PutMapping("/update")
    public AccountRequest updateAccount(AccountRequest accountRequest) throws Exception {
        return accountService.updateAccount(accountRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
    }

    @GetMapping("/list")
    public Iterable<Account> searchAllAccounts() {
        return accountService.searchAllAccounts();
    }

}
