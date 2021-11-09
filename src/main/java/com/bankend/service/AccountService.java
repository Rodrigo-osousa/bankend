package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Account;
import com.bankend.model.entity.Customer;
import com.bankend.model.request.AccountRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;


@Service
public class AccountService {

    Logger logger = Logger.getLogger(AccountService.class.getName());


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public AccountRequest createAccount(AccountRequest accountRequest) throws BusinessException {

        logger.info("createAccount: " + accountRequest.toString());
        Optional<Customer> client = customerRepository.findByDocumentNumber(accountRequest.getDocumentNumber());

        if (client.isEmpty()){
            logger.info("Client not exist");
            throw new BusinessException("Client not exist");
        }

        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAgency(accountRequest.getAgency());
        account.setBalance(accountRequest.getBalance());
        account.setCredit(accountRequest.getCredit());
        account.setInactive(accountRequest.getInactive());
        account.setClient(client.get());

        accountRepository.save(account);
        return accountRequest;
    }


    public AccountRequest updateAccount(AccountRequest accountRequest) throws BusinessException {
        Optional<Account> findAccount = accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
        Optional<Customer> client = customerRepository.findByDocumentNumber(accountRequest.getDocumentNumber());
        if (findAccount.isEmpty()){
            logger.info("Account or Client not exist");
            throw new BusinessException("Account or Client not exist");
        }

        Account account = new Account();
        account.setId(findAccount.get().getId());
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAgency(accountRequest.getAgency());
        account.setBalance(accountRequest.getBalance());
        account.setCredit(accountRequest.getCredit());
        account.setInactive(accountRequest.getInactive());
        account.setClient(client.get());

        accountRepository.save(account);
        return accountRequest;
    }

    public Iterable<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> searchAccountById(int id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    }

