package com.bankend.controllers;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Client;
import com.bankend.model.request.ClientRequest;
import com.bankend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientRequest clientRequest) throws BusinessException {
        try {
            clientService.createClient(clientRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateClient(@Valid ClientRequest clientRequest) throws BusinessException {
        try {
            clientService.updateClient(clientRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/list")
    public Iterable<Client> searchClient() {
        return clientService.searchClient();
    }

    @GetMapping("/{id}")
    public Optional<Client> searchClientById(@PathVariable int id) throws Exception {
        return clientService.searchClientById(id);
    }


    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

}
