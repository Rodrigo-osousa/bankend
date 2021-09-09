package com.bankend.bankend.controller;

import com.bankend.bankend.entity.model.Client;
import com.bankend.bankend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/new")
    @ResponseBody
    public Client creatClient(@Valid Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/list")
    public Iterable<Client> obtainClient() {
        return clientService.obtainClient();
    }

    @GetMapping("/{id}")
    public Optional<Client> obtainClienteId(@PathVariable int id) {
        return clientService.obtainClientId(id);
    }

    @PutMapping("/update")
    public Client updateClient(@Valid Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

}
