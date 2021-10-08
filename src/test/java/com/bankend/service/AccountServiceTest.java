package com.bankend.service;

import com.bankend.model.entity.Account;
import com.bankend.model.entity.request.AccountRequest;
import com.bankend.model.entity.request.ClientRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @BeforeAll
    void setUp() throws Exception {
        ClientRequest clientRequest = new ClientRequest("New Client 0", "Str. BNH", "000000000");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest("00000", 15, 100.00, 100.00, true, "000000000");
        accountService.createAccount(accountRequest);

        ClientRequest clientRequest2 = new ClientRequest("New Client", "Str. BNH", "999999999");
        clientService.createClient(clientRequest2);

        AccountRequest accountRequest2 = new AccountRequest("00009", 15, 100.00, 100.00, true, "999999999");
        accountService.createAccount(accountRequest2);

    }


    @Test
    void createAccount() throws Exception {

        ClientRequest clientRequest = new ClientRequest("New Client", "Str. BNH", "111111111");
        clientService.createClient(clientRequest);

        AccountRequest accountRequest = new AccountRequest("00001", 15, 100.00, 100.00, true, "111111111");
        accountService.createAccount(accountRequest);


        Optional<Account> canCreateAccount = accountRepository.findByAccountNumber("00001");
        Assertions.assertEquals("111111111", canCreateAccount.get().getClient().getDocumentNumber());
    }


    @Test
    void updateAccount() throws Exception {


        AccountRequest accountToUpdate = new AccountRequest();
        accountToUpdate.setAccountNumber("00000");
        accountToUpdate.setBalance(0.0);
        accountToUpdate.setCredit(10.000);
        accountToUpdate.setAgency(1345);
        accountToUpdate.setInactive(true);
        accountToUpdate.setDocumentNumber("000000000");
        accountService.updateAccount(accountToUpdate);

        Optional<Account> accountUpdated = accountRepository.findByAccountNumber("00000");
        Assertions.assertEquals(0.0, accountUpdated.get().getBalance());
    }

    @Test
    void searchAllAccounts() {

        List<Account> allAccounts = (List<Account>) accountRepository.findAll();
        Assertions.assertTrue(allAccounts.size() >= 1);

    }

    @Test
    void searchAccountById() {


        Optional<Account> accountIsCorrect = accountRepository.findById(1);
        Assertions.assertEquals("000000000", accountIsCorrect.get().getClient().getDocumentNumber());

    }

    @Test
    void deleteAccount() {

        accountService.deleteAccount(2);
        List<Account> canDeleteAccount = (List<Account>) accountRepository.findAll();
        boolean haveAccount = canDeleteAccount.size() == 1;
        Assertions.assertTrue(haveAccount);

    }

}