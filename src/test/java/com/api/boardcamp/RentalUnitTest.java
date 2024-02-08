package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.dtos.RentalsDTO;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.repositories.CustomerRepository;
import com.api.boardcamp.repositories.RentalRepository;
import com.api.boardcamp.services.RentalsService;

@SpringBootTest
public class RentalUnitTest {
    @InjectMocks
    RentalsService rentalsService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void customerNotExist_whenCreateNewRental_thenThrowsNotFoundError(){
        RentalsDTO rentalDto = new RentalsDTO(1L, 1L, 2);
        doReturn(Optional.empty()).when(customerRepository).findById(any());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> rentalsService.saveNewRental(rentalDto));

        assertNotNull(exception);
        assertEquals("user not found!", exception.getMessage());
		verify(rentalRepository, times(0)).save(any());
		verify(customerRepository, times(1)).findById(any());
    }
}
