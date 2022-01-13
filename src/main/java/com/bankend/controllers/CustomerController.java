package com.bankend.controllers;

import com.bankend.exception.BusinessException;
import com.bankend.model.entity.Customer;
import com.bankend.model.request.CustomerRequest;
import com.bankend.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
@Api(value = "Customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Validated
    @PostMapping("/new")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) throws BusinessException {
        try {
            customerService.createCustomer(customerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@Valid CustomerRequest customerRequest) throws BusinessException {
        try {
            customerService.updateCustomer(customerRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated");
        } catch (BusinessException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/list")
    public Iterable<Customer> searchCustomer() {
        return customerService.searchCustomer();
    }

    @GetMapping("/{id}")
    public Optional<Customer> searchCustomerById(@PathVariable int id) throws Exception {
        return customerService.searchCustomerById(id);
    }


    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }

}
