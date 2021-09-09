package com.bankend.bankend.repository;

import com.bankend.bankend.entity.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
