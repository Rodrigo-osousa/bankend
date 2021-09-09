package com.bankend.bankend.repository;

import com.bankend.bankend.entity.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository <Account, Integer> {
}
