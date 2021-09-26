package com.bankend.service;

import com.bankend.model.entity.Account;
import com.bankend.model.entity.Client;
import com.bankend.model.entity.request.AccountRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.ClientRepository;
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
    private ClientRepository clientRepository;


    public AccountRequest createAccount(AccountRequest accountRequest) throws Exception {

        logger.info("createAccount: " + accountRequest.toString());
        Optional<Client> client = clientRepository.findByDocumentNumber(accountRequest.getDocumentNumber());

        if (client.isEmpty()){
            logger.info("Cliente não cadastrado");
            throw new Exception();
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

    public AccountRequest updateAccount(AccountRequest accountRequest) throws Exception {
        logger.info("Atualizando  conta");
        Optional<Account> accountExist = accountRepository.findByAccountNumber(accountRequest.getDocumentNumber());
        if (accountExist.isEmpty()){
            logger.info("Conta inexistente");
            throw  new Exception();
        }
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAgency(accountRequest.getAgency());
        account.setBalance(accountRequest.getBalance());
        account.setCredit(accountRequest.getCredit());
        account.setInactive(accountRequest.getInactive());


        accountRepository.save(account);
        return accountRequest;
    }

    public Iterable<Account> searchAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> searchAccountById(int id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}
