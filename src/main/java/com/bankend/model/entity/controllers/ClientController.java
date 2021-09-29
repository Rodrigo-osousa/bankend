package com.bankend.model.entity.controllers;

import com.bankend.model.entity.Client;
import com.bankend.model.entity.request.ClientRequest;
import com.bankend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Validated
    @PostMapping("/new")
    public void createClient(@Valid @RequestBody ClientRequest clientRequest) throws Exception {
        clientService.createClient(clientRequest);

    }

    @GetMapping("/list")
    public Iterable<Client> obtainClient() {
        return clientService.obtainClient();
    }

    @GetMapping("/{id}")
    public Optional<Client> obtainClienteId(@PathVariable int id) throws Exception {
        return clientService.obtainClientId(id);
    }

    @PutMapping("/update")
    public ClientRequest updateClient(@Valid ClientRequest clientRequest) throws Exception {
        return clientService.upadateClient(clientRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

}
