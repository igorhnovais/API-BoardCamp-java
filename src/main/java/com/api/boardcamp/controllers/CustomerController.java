package com.api.boardcamp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.boardcamp.dtos.CustomerDTO;
import com.api.boardcamp.models.CustomerModel;
import com.api.boardcamp.services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    final CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerModel> postCustomer(@RequestBody @Valid CustomerDTO body){
        CustomerModel customer = customerService.saveNewCustomer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{id}")
    public CustomerModel getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }
}
