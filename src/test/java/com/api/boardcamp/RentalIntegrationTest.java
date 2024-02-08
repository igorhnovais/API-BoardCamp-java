package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.boardcamp.dtos.RentalsDTO;
import com.api.boardcamp.models.CustomerModel;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.repositories.CustomerRepository;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDb(){
        rentalRepository.deleteAll();
        gameRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void validRental_whenCreateNewRental_thenCreateRentalSucess(){
        GameModel Game = new GameModel(null,"name", "image", 3, 1500);
        GameModel createdGame = gameRepository.save(Game);

        CustomerModel customer = new CustomerModel(null, "name", "12345678910");
        CustomerModel createdCustomer = customerRepository.save(customer);

        RentalsDTO rental = new RentalsDTO(createdCustomer.getId(), createdGame.getId(), 3);
        HttpEntity<RentalsDTO> body = new HttpEntity<>(rental);

        ResponseEntity<GameModel> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, GameModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, rentalRepository.count());
    }
}
