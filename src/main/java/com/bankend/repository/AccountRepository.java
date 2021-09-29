package com.bankend.repository;

import com.bankend.model.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository <Account, Integer> {
    Optional<Account> findByAccountNumber (String accountNumber);

}

