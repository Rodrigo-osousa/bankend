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

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setAddress("rua aqui");
        clientRequest.setDocumentNumber("123123123");
        clientRequest.setName("Rudrigo");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAgency(5000);
        accountRequest.setBalance(2000.00);
        accountRequest.setCredit(2000.0);
        accountRequest.setDocumentNumber("123123123");
        accountService.createAccount(accountRequest);
    }

    @Test
    void updateAccount() throws Exception {

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setAddress("rua ali");
        clientRequest.setDocumentNumber("999999999");
        clientRequest.setName("Tapuru");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setDocumentNumber("999999999");
        accountRequest.setCredit(10.000);
        accountRequest.setBalance(10.000);
        accountRequest.setAgency(1345);
        accountRequest.setInactive(true);
        accountService.createAccount(accountRequest);

        Account account = new Account();
        account.setIdAccount(1);
        account.setBalance(1000.0);
        account.setCredit(10.000);
        account.setAgency(1345);
        account.setInactive(true);
        accountService.updateAccount(account);

        Optional<Account> accountUpdated = accountRepository.findById(1);
        Assertions.assertEquals(1000.0, accountUpdated.get().getBalance());
    }

    @Test
    void searchAllAccounts() throws Exception {

            ClientRequest clientRequest1 = new ClientRequest();
            clientRequest1.setAddress("rua ali");
            clientRequest1.setDocumentNumber("333333333");
            clientRequest1.setName("fulano");
            clientService.createClient(clientRequest1);

            AccountRequest accountRequest1 = new AccountRequest();
            accountRequest1.setAgency(5000);
            accountRequest1.setBalance(2000.00);
            accountRequest1.setCredit(2000.0);
            accountRequest1.setDocumentNumber("333333333");
            accountService.createAccount(accountRequest1);

            ClientRequest clientRequest2 = new ClientRequest();
            clientRequest2.setAddress("rua aculá");
            clientRequest2.setDocumentNumber("444444444");
            clientRequest2.setName("delcrano");
            clientService.createClient(clientRequest2);

            AccountRequest accountRequest2 = new AccountRequest();
            accountRequest2.setAgency(5100);
            accountRequest2.setBalance(2000.00);
            accountRequest2.setCredit(2000.0);
            accountRequest2.setDocumentNumber("444444444");
            accountService.createAccount(accountRequest2);

            List<Account> allAccounts = (List<Account>) accountRepository.findAll();
            Assertions.assertEquals(2, allAccounts.size());

    }

    @Test
    void serchAccountById() throws Exception {

            ClientRequest clientRequest1 = new ClientRequest();
            clientRequest1.setAddress("rua ali");
            clientRequest1.setDocumentNumber("777777777");
            clientRequest1.setName("fulano");
            clientService.createClient(clientRequest1);

            AccountRequest accountRequest1 = new AccountRequest();
            accountRequest1.setAgency(5000);
            accountRequest1.setBalance(2000.00);
            accountRequest1.setCredit(2000.0);
            accountRequest1.setDocumentNumber("777777777");
            accountService.createAccount(accountRequest1);


            Optional<Account> accountIsCorrect = accountRepository.findById(4);
            Assertions.assertEquals("777777777", accountIsCorrect.get().getClient().getDocumentNumber());

    }

    @Test
    @Disabled
    void deleteAccount() {
    }
}