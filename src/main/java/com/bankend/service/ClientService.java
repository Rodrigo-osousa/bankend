package com.bankend.service;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Client;
import com.bankend.model.request.ClientRequest;
import com.bankend.repository.AccountRepository;
import com.bankend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;


@Service

public class ClientService {

    Logger logger = Logger.getLogger(ClientService.class.getName());

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;


    public void createClient(ClientRequest clientRequest) throws BusinessException{

        logger.info("=========> Crindo cliente: " + clientRequest.toString());

        boolean clientExist = verifyIfClientExists(clientRequest.getDocumentNumber());

        if (clientExist) {
            logger.info("Error when trying to register existing Client");
            throw new BusinessException("Error when trying to register existing Client");
        }

        Client client = new Client();
        client.setAddress(clientRequest.getAddress());
        client.setDocumentNumber(clientRequest.getDocumentNumber());
        client.setName(clientRequest.getName());

        clientRepository.save(client);
        logger.info("Client saved successfully: " + client.getDocumentNumber());
    }


    public ClientRequest updateClient(ClientRequest clientRequest) throws BusinessException {
        Optional<Client> findClientById = clientRepository.findById(clientRequest.getId());

        if (findClientById.isEmpty()) {
            logger.info("Error trying to locate Client");
            throw new BusinessException("Error trying to locate Client");
        }
        Client client = new Client();
        client.setAddress(clientRequest.getAddress());
        client.setDocumentNumber(clientRequest.getDocumentNumber());
        client.setName(clientRequest.getName());
        client.setId(clientRequest.getId());

        clientRepository.save(client);
        logger.info("Client updated successfully" + client.getName());
        return clientRequest;
    }

    public Iterable<Client> searchClient() {
        return clientRepository.findAll();
    }

    public Optional<Client> searchClientById(int id) throws BusinessException {

        Optional<Client> clientReturn = clientRepository.findById(id);
        if (clientReturn.isEmpty()) {
            throw new BusinessException("Client not Exist");
        }
        return clientReturn;
    }
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
        logger.info("Client deleted");
    }

    private boolean verifyIfClientExists(String documentNumber) {
        Optional<Client> clientFromDatabase = clientRepository.findByDocumentNumber(documentNumber);
        return clientFromDatabase.isPresent();
    }
}
