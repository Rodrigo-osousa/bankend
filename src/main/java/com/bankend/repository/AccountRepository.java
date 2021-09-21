package com.bankend.repository;

import com.bankend.model.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository <Account, Integer> {
}
