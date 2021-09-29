package com.bankend.service;

import com.bankend.model.entity.Client;
import com.bankend.model.entity.request.ClientRequest;
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


    public void createClient(ClientRequest clientRequest) throws Exception {

        logger.info("=========> Crindo cliente: " + clientRequest.toString());

        boolean clientExist = verifyIfExists(clientRequest.getDocumentNumber());

        if (clientExist) {
            logger.info("Erro ao tentar cadastrar cliente já existente");
            throw new Exception();
        }

        Client client = new Client();
        client.setAddress(clientRequest.getAddress());
        client.setDocumentNumber(clientRequest.getDocumentNumber());
        client.setName(clientRequest.getName());

        clientRepository.save(client);
        logger.info("Cliente salvo com sucesso: " + client.getDocumentNumber());
    }


    public ClientRequest upadateClient(ClientRequest clientRequest) throws Exception {
        Optional<Client> findClientById = clientRepository.findById(clientRequest.getId());

        if (findClientById.isEmpty()) {
            logger.info("Erro ao tentar localizar cliente");
            throw new Exception();
        }
        Client client = new Client();
        client.setAddress(clientRequest.getAddress());
        client.setDocumentNumber(clientRequest.getDocumentNumber());
        client.setName(clientRequest.getName());
        client.setId(clientRequest.getId());

        clientRepository.save(client);
        logger.info("Cliente atualizado com sucesso" + client.getName());
        return clientRequest;
    }

    public Iterable<Client> obtainClient() {
        return clientRepository.findAll();
    }

    public Optional<Client> obtainClientId(int id) throws Exception {

        Optional<Client> clientReturn = clientRepository.findById(id);
        if (clientReturn.isEmpty()) {
            throw new Exception("Client not Exist");
        }
        return clientReturn;
    }
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
        logger.info("Client deleted");
    }

    private boolean verifyIfExists(String documentNumber) {
        Optional<Client> clientFromDatabase = clientRepository.findByDocumentNumber(documentNumber);
        return clientFromDatabase.isPresent();
    }
}
