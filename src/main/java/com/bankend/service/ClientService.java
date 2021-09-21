package com.bankend.service;

import com.bankend.model.entity.Client;
import com.bankend.model.entity.request.ClientRequest;
import com.bankend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;


@Service
public class ClientService {

    Logger logger = Logger.getLogger(ClientService.class.getName());

    @Value("${warning.message.controller}")
    private String message;

    @Autowired
    private ClientRepository clientRepository;

    public Client updateClient(Client client) {
        clientRepository.save(client);
        return client;
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

    public void createClient(ClientRequest clientRequest) throws Exception {
        logger.info("Mensagem do properties: " + message);
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

    private boolean verifyIfExists(String documentNumber) {
        Optional<Client> clientFromDatabase = clientRepository.findByDocumentNumber(documentNumber);
        return clientFromDatabase.isPresent();
    }


    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
