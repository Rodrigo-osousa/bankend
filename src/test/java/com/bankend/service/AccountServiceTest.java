package com.bankend.service;

import com.bankend.model.entity.request.AccountRequest;
import com.bankend.model.entity.request.ClientRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.ClientRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Disabled
    void updateAccount() {
    }

    @Test
    @Disabled
    void obtainAccount() {
    }

    @Test
    @Disabled
    void obtainAccountId() {
    }

    @Test
    @Disabled
    void deleteAccount() {
    }
}