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

        ClientRequest clientRequest = new ClientRequest("Client Test", "Str. Test", "123123123");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest(5000,"1", 2000.00, 2000.00, true, "123123123");
        accountService.createAccount(accountRequest);
    }

    @Test
    void updateAccount() throws Exception {

        ClientRequest clientRequest = new ClientRequest("Client Test 2", "Str. Test2", "999999999");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest(1234,"5", 1000.00, 1000.0, true, "999999999");
        accountService.createAccount(accountRequest);

        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("5");
        account.setBalance(0.0);
        account.setCredit(10.000);
        account.setAgency(1345);
        account.setInactive(true);
        accountService.updateAccount(account);

        Optional<Account> accountUpdated = accountRepository.findById(1);
        Assertions.assertEquals(0.0, accountUpdated.get().getBalance());
    }

    @Test
    void searchAllAccounts() throws Exception {

        ClientRequest clientRequest1 = new ClientRequest("Client Test 3", "Str. Neverland", "333333333");
        clientService.createClient(clientRequest1);

        AccountRequest accountRequest1 = new AccountRequest(5000,"2", 1.0, 5.0, false, "333333333");
        accountService.createAccount(accountRequest1);

        ClientRequest clientRequest2 = new ClientRequest("Client Test 4", "Str. FarfarWay", "444444444");
        clientService.createClient(clientRequest2);

        AccountRequest accountRequest2 = new AccountRequest(5000,"3", 50.00,100.00, true, "444444444");
        accountService.createAccount(accountRequest2);

        List<Account> allAccounts = (List<Account>) accountRepository.findAll();
        Assertions.assertEquals(2, allAccounts.size());

    }

    @Test
    void serchAccountById() throws Exception {

        ClientRequest clientRequest1 = new ClientRequest("Client Test 5", "Str. Wakanda", "777777777");
        clientService.createClient(clientRequest1);

        AccountRequest accountRequest1 = new AccountRequest(5000,"4", 2.000, 3.000, false, "777777777");
        accountService.createAccount(accountRequest1);


        Optional<Account> accountIsCorrect = accountRepository.findById(4);
        Assertions.assertEquals("777777777", accountIsCorrect.get().getClient().getDocumentNumber());

    }

    @Test
    @Disabled
    void deleteAccount() {

    }
}