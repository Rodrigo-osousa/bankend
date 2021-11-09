package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Customer;
import com.bankend.model.request.CustomerRequest;
import com.bankend.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;


    @BeforeAll
    void setUp() throws Exception {
        CustomerRequest customerRequest1 = new CustomerRequest("New Client 1", "Str. Stonehange", "968574321");
        customerService.createCustomer(customerRequest1);

        CustomerRequest customerRequest2 = new CustomerRequest("New Client 2", "Str. Stonehange", "968574322");
        customerService.createCustomer(customerRequest2);
    }



    @Test
    void createClient() throws Exception {

        CustomerRequest customerRequest3 = new CustomerRequest("New Client 3", "Str. SpringField", "968574323");
        customerService.createCustomer(customerRequest3);

        Optional<Customer> canCreateClient = customerRepository.findByDocumentNumber("968574323");
        Assertions.assertEquals("New Client 3", canCreateClient.get().getName());

    }

    @Test
    void updateClient() throws Exception {

        CustomerRequest clientToUpdate = new CustomerRequest();
        clientToUpdate.setId(1);
        clientToUpdate.setName("New Client Updated");
        clientToUpdate.setAddress("Str. Stonehange");
        clientToUpdate.setDocumentNumber("968574321");
        customerService.updateCustomer(clientToUpdate);

        Optional<Customer> canUpdateClient = customerRepository.findByDocumentNumber("968574321");
        Assertions.assertEquals("New Client Updated", canUpdateClient.get().getName());

    }

    @Test
    void searchClient() {
        List<Customer> findAllCustomers = (List<Customer>) customerRepository.findAll();
        Assertions.assertTrue(findAllCustomers.size() >= 1);

    }

    @Test
    void createClientException() throws BusinessException {
        CustomerRequest customerRequest5 = new CustomerRequest("New Client 1", "Str. Stonehange", "968574321");

        Assertions.assertThrows(BusinessException.class, () -> {
            customerService.createCustomer(customerRequest5);
        });

    }

    @Test
    void updateClientException() throws BusinessException {
        CustomerRequest clientToUpdateEx = new CustomerRequest();
        clientToUpdateEx.setId(13);
        clientToUpdateEx.setName("New Client Updated");
        clientToUpdateEx.setAddress("Str. Stonehange");
        clientToUpdateEx.setDocumentNumber("pãoDeBatata");

        Assertions.assertThrows(BusinessException.class, () -> {
            customerService.updateCustomer(clientToUpdateEx);
        });

    }


    @Test
    void searchClientById() {
        Optional<Customer> findClientById = customerRepository.findById(1);
        Assertions.assertEquals("968574321", findClientById.get().getDocumentNumber());
    }

    @Test
    void deleteClient() {
        customerService.deleteCustomer(2);
        Optional<Customer> customerDeleted = customerRepository.findById(2);
        boolean canDelete = customerDeleted.isEmpty();
        Assertions.assertTrue(canDelete);
    }

}