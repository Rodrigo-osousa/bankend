package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Customer;
import com.bankend.model.request.CustomerRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;


@Service

public class CustomerService {

    Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;


    public void createCustomer(CustomerRequest customerRequest) throws BusinessException{

        logger.info("Creating Customer: " + customerRequest.toString());

        boolean customerExist = verifyIfCustomerExists(customerRequest.getDocumentNumber());

        if (customerExist) {
            logger.info("Error when trying to register existing Customer");
            throw new BusinessException("Error when trying to register existing Customer");
        }

        Customer customer = new Customer();
        customer.setAddress(customerRequest.getAddress());
        customer.setDocumentNumber(customerRequest.getDocumentNumber());
        customer.setName(customerRequest.getName());

        customerRepository.save(customer);
        logger.info("Customer saved successfully: " + customer.getDocumentNumber());
    }


    public CustomerRequest updateCustomer(CustomerRequest customerRequest) throws BusinessException {
        Optional<Customer> findCustomerById = customerRepository.findById(customerRequest.getId());

        if (findCustomerById.isEmpty()) {
            logger.info("Error trying to locate Customer");
            throw new BusinessException("Error trying to locate Customer");
        }
        Customer customer = new Customer();
        customer.setAddress(customerRequest.getAddress());
        customer.setDocumentNumber(customerRequest.getDocumentNumber());
        customer.setName(customerRequest.getName());
        customer.setId(customerRequest.getId());

        customerRepository.save(customer);
        logger.info("Customer updated successfully" + customer.getName());
        return customerRequest;
    }

    public Iterable<Customer> searchCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> searchCustomerById(int id) throws BusinessException {

        Optional<Customer> customerReturn = customerRepository.findById(id);
        if (customerReturn.isEmpty()) {
            throw new BusinessException("Customer not Exist");
        }
        return customerReturn;
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
        logger.info("Customer deleted");
    }

    private boolean verifyIfCustomerExists(String documentNumber) {
        Optional<Customer> customerFromDatabase = customerRepository.findByDocumentNumber(documentNumber);
        return customerFromDatabase.isPresent();
    }
}
