package com.bankend.repository;

import com.bankend.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByDocumentNumber(String documentNumber);
}
