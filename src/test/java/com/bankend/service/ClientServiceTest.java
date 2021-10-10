package com.bankend.service;

import com.bankend.model.entity.Client;
import com.bankend.model.request.ClientRequest;
import com.bankend.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;


    @BeforeAll
    void setUp() throws Exception {
        ClientRequest clientRequest1 = new ClientRequest("New Client 1", "Str. Stonehange", "968574321");
        clientService.createClient(clientRequest1);

        ClientRequest clientRequest2 = new ClientRequest("New Client 2", "Str. Stone Range", "968574322");
        clientService.createClient(clientRequest2);
    }


    @Test
    void createClient() throws Exception {

        ClientRequest clientRequest3 = new ClientRequest("New Client 3", "Str. SpringField", "968574323");
        clientService.createClient(clientRequest3);

        Optional<Client> canCreateClient = clientRepository.findByDocumentNumber("968574323");
        Assertions.assertEquals("New Client 3", canCreateClient.get().getName());

    }

    @Test
    void updateClient() throws Exception {

        ClientRequest clientToUpdate = new ClientRequest();
        clientToUpdate.setId(1);
        clientToUpdate.setName("New Client Updated");
        clientToUpdate.setAddress("Str. Stonehange");
        clientToUpdate.setDocumentNumber("968574321");
        clientService.updateClient(clientToUpdate);

        Optional<Client> canUpdateClient = clientRepository.findByDocumentNumber("968574321");
        Assertions.assertEquals("New Client Updated", canUpdateClient.get().getName());

    }

    @Test
    void searchClient() {
       List<Client> findAllClients = (List<Client>) clientRepository.findAll();
       Assertions.assertTrue(findAllClients.size() >= 1);

    }

    @Test
    void searchClientById() {
        Optional<Client> findClientById = clientRepository.findById(1);
        Assertions.assertEquals("968574321", findClientById.get().getDocumentNumber());
    }

    @Test
    void deleteClient() {
        clientService.deleteClient(2);
        Optional<Client> clientDeleted = clientRepository.findById(2);
        boolean canDelete = clientDeleted.isEmpty();
        Assertions.assertTrue(canDelete);
    }
}