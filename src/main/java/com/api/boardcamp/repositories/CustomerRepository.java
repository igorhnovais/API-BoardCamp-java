package com.api.boardcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.boardcamp.models.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{
    boolean existsByCpf(String cpf);
}
