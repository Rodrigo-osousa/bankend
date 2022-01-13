package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Account;
import com.bankend.model.request.AccountRequest;
import com.bankend.model.request.CustomerRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;



    @BeforeAll
    void setUp() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest("New Client 0", "Str. BNH", "000000000");
        customerService.createCustomer(customerRequest);

        AccountRequest accountRequest = new AccountRequest("00000", 15, 100.00, 100.00, true, "000000000");
        accountService.createAccount(accountRequest);

        CustomerRequest customerRequest2 = new CustomerRequest("New Client", "Str. BNH", "999999999");
        customerService.createCustomer(customerRequest2);

        AccountRequest accountRequest2 = new AccountRequest("00009", 15, 100.00, 100.00, true, "999999999");
        accountService.createAccount(accountRequest2);

    }




    @Test
    void createAccount() throws Exception {

        CustomerRequest customerRequest = new CustomerRequest("New Client", "Str. BNH", "111111111");
        customerService.createCustomer(customerRequest);

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

        List<Account> findAllAccounts = (List<Account>) accountRepository.findAll();
        Assertions.assertTrue(findAllAccounts.size() >= 1);

    }

    @Test
    void searchAccountById() {

        Optional<Account> findAccountById = accountRepository.findById(1);
        Assertions.assertEquals("000000000", findAccountById.get().getClient().getDocumentNumber());

    }

    @Test
    void createAccountException() throws BusinessException {
        CustomerRequest customerRequest = new CustomerRequest("New Client", "Str. BNH", "111111112");
        customerService.createCustomer(customerRequest);

        AccountRequest accountRequest = new AccountRequest("00001", 15, 100.00, 100.00, true, "111111113");

        Assertions.assertThrows(BusinessException.class, () -> {
            accountService.createAccount(accountRequest);
        });
    }

    @Test
    void updateAccountException() throws BusinessException {

        AccountRequest accountToUpdateEx = new AccountRequest();
        accountToUpdateEx.setAccountNumber("11111");
        accountToUpdateEx.setBalance(0.0);
        accountToUpdateEx.setCredit(10.000);
        accountToUpdateEx.setAgency(1345);
        accountToUpdateEx.setInactive(true);
        accountToUpdateEx.setDocumentNumber("000000001");

        Assertions.assertThrows(BusinessException.class, () -> {
            accountService.updateAccount(accountToUpdateEx);

        });


    }

    @Test
    void deleteAccount() {

        accountService.deleteAccount(2);
        List<Account> canDeleteAccount = (List<Account>) accountRepository.findAll();
        boolean haveAccount = canDeleteAccount.size() == 1;
        Assertions.assertTrue(haveAccount);

    }

}