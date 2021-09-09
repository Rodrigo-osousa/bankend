package com.bankend.bankend.service;

import com.bankend.bankend.entity.model.Client;
import com.bankend.bankend.repository.ClientRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client updateClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    public Client createClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    public Iterable<Client> obtainClient() {
        return clientRepository.findAll();
    }

    public Optional<Client> obtainClientId(int id) {
        return clientRepository.findById(id);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
