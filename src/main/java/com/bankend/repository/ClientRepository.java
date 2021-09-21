package com.bankend.repository;

import com.bankend.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findByDocumentNumber(String documentNumber);
}
