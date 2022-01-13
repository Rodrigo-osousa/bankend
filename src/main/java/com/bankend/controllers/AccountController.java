package com.bankend.controllers;


import com.bankend.model.entity.Account;
import com.bankend.model.request.AccountRequest;
import com.bankend.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@Api(value = "Account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping("/new")
    @ApiOperation(value = "New Account")
    @ResponseBody
    public AccountRequest createAccount(@Valid @RequestBody AccountRequest accountRequest) throws Exception {
        return accountService.createAccount(accountRequest);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search Account by ID")
    public Optional<Account> searchAccountById(@PathVariable int id){
        return accountService.searchAccountById(id);
    }


    @PutMapping("/update")
    @ApiOperation(value = "Update Account")
    public AccountRequest updateAccount(AccountRequest accountRequest) throws Exception {
        return accountService.updateAccount(accountRequest);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Account")
    public void deleteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
    }

    @GetMapping("/list")
    @ApiOperation(value = "List all Accounts")
    public Iterable<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

}
