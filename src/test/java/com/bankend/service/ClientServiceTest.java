package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Client;
import com.bankend.model.request.ClientRequest;
import com.bankend.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

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

        ClientRequest clientRequest2 = new ClientRequest("New Client 2", "Str. Stonehange", "968574322");
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
    void createClientException() throws BusinessException {
        ClientRequest clientRequest5 = new ClientRequest("New Client 1", "Str. Stonehange", "968574321");

        Assertions.assertThrows(BusinessException.class, () -> {
            clientService.createClient(clientRequest5);
        });

    }

    @Test
    void updateClientException() throws BusinessException {
        ClientRequest clientToUpdateEx = new ClientRequest();
        clientToUpdateEx.setId(13);
        clientToUpdateEx.setName("New Client Updated");
        clientToUpdateEx.setAddress("Str. Stonehange");
        clientToUpdateEx.setDocumentNumber("pãoDeBatata");

        Assertions.assertThrows(BusinessException.class, () -> {
            clientService.updateClient(clientToUpdateEx);
        });

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