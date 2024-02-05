package com.api.boardcamp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.CustomerDTO;
import com.api.boardcamp.exceptions.ConflictException;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.models.CustomerModel;
import com.api.boardcamp.repositories.CustomerRepository;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel saveNewCustomer(CustomerDTO dto) {
        boolean cpfExists = customerRepository.existsByCpf(dto.getCpf());

        if(cpfExists){
            throw new ConflictException("this cpf is already registred!");
        }

        CustomerModel customer = new CustomerModel(dto);
        return customerRepository.save(customer);
    }

    public CustomerModel findCustomerById(Long id){
        Optional<CustomerModel> customerExists = customerRepository.findById(id);
        
        if(!customerExists.isPresent()) {
            throw new NotFoundException("this user does not exist");
        }

        return customerExists.get();
    }
}
