package com.bankend.bankend.controller;


import com.bankend.bankend.entity.model.Account;
import com.bankend.bankend.repository.AccountRepository;
import com.bankend.bankend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/new")
    @ResponseBody
    public Account createAcount(@Valid Account account){
        return accountService.createAccount(account);
    }



}
