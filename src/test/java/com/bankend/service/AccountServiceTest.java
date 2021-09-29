package com.bankend.service;

import com.bankend.model.entity.Account;
import com.bankend.model.entity.request.AccountRequest;
import com.bankend.model.entity.request.ClientRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;


    @Test
    void createAccount() throws Exception {

        ClientRequest clientRequest = new ClientRequest(1,"Client Test", "Str. Test", "123123123");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest(2,"12345",10,50.00,100.00,true,"111111111");
        accountService.createAccount(accountRequest);
    }

    @Test
    void updateAccount() throws Exception {

        ClientRequest clientRequest = new ClientRequest(3,"Client Test 2", "Str. Test2", "999999999");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest(3,"12346",10,50.00,100.00,true,"222222222");
        accountService.createAccount(accountRequest);

        Account account = new Account();
        account.setId(3);
        account.setAccountNumber("12346");
        account.setBalance(0.0);
        account.setCredit(10.000);
        account.setAgency(1345);
        account.setInactive(true);
        accountService.updateAccount(accountRequest);

        Optional<Account> accountUpdated = accountRepository.findById(2);
        Assertions.assertEquals(0.0, accountUpdated.get().getBalance());
    }

    @Test
    void searchAllAccounts() throws Exception {

        ClientRequest clientRequest1 = new ClientRequest(4,"Client Test 3", "Str. Neverland", "333333333");
        clientService.createClient(clientRequest1);

        AccountRequest accountRequest1 = new AccountRequest(3,"12344",10,100.00,10.00,false,"333333333");
        accountService.createAccount(accountRequest1);

        ClientRequest clientRequest2 = new ClientRequest(5,"Client Test 4", "Str. FarfarWay", "444444444");
        clientService.createClient(clientRequest2);

        AccountRequest accountRequest2 = new AccountRequest(4,"12349",10,5.00,10.00,false,"444444444");
        accountService.createAccount(accountRequest2);

        List<Account> allAccounts = (List<Account>) accountRepository.findAll();
        Assertions.assertEquals(2, allAccounts.size());

    }

    @Test
    void serchAccountById() throws Exception {

        ClientRequest clientRequest1 = new ClientRequest(6,"Client Test 5", "Str. Wakanda", "777777777");
        clientService.createClient(clientRequest1);

        AccountRequest accountRequest1 = new AccountRequest(5,"12341",10,10.10,5.00,false,"555555555");
        accountService.createAccount(accountRequest1);


        Optional<Account> accountIsCorrect = accountRepository.findById(4);
        Assertions.assertEquals("777777777", accountIsCorrect.get().getClient().getDocumentNumber());

    }

    @Test
    @Disabled
    void deleteAccount() {

    }
}