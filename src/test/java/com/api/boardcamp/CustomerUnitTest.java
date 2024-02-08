package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.dtos.CustomerDTO;
import com.api.boardcamp.exceptions.ConflictException;
import com.api.boardcamp.repositories.CustomerRepository;
import com.api.boardcamp.services.CustomerService;

@SpringBootTest
public class CustomerUnitTest {
    @InjectMocks
    CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void repeatCpfUser_whenCreateNewUser_thenThrowsConflictError(){
        CustomerDTO customerDto = new CustomerDTO("name", "01234567890");
        doReturn(true).when(customerRepository).existsByCpf(any());

        ConflictException exception = assertThrows(ConflictException.class, () -> customerService.saveNewCustomer(customerDto));
        assertNotNull(exception);
        assertEquals("this cpf is already registred!", exception.getMessage());
        verify(customerRepository, times(0)).save(any());
        verify(customerRepository, times(1)).existsByCpf(any());
    }
}
