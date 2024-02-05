package com.api.boardcamp.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.RentalsDTO;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.exceptions.UnprocessableEntityException;
import com.api.boardcamp.models.CustomerModel;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.models.RentalsModel;
import com.api.boardcamp.repositories.CustomerRepository;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.RentalRepository;

@Service
public class RentalsService {

    final RentalRepository rentalRepository;
    final GameRepository gameRepository;
    final CustomerRepository customerRepository;

    Date dateNow = new Date();
    String data = new SimpleDateFormat("dd-MM-yyyy").format(dateNow);

    RentalsService(
        RentalRepository rentalRepository,
        GameRepository gameRepository,
        CustomerRepository customerRepository
    ){
        this.rentalRepository = rentalRepository;
        this.gameRepository = gameRepository;
        this.customerRepository = customerRepository;
    }

    public RentalsModel saveNewRental(RentalsDTO dto){
        Optional<CustomerModel> customerExists = customerRepository.findById(dto.getCustomerId());

        if(!customerExists.isPresent()){
            throw new NotFoundException("user not found!");
        }

        Optional<GameModel> gameExists = gameRepository.findById(dto.getGameId());

        if(!gameExists.isPresent()){
            throw new NotFoundException("Game not found!");
        }

        GameModel game = gameExists.get();
        CustomerModel customer = customerExists.get();

        if(game.getStockTotal() < 1){
            throw new UnprocessableEntityException("No Stock available");
        }

        GameModel newGame = new GameModel(game, game.getStockTotal()-1);
        gameRepository.save(newGame);

        int priceTotal = game.getPricePerDay() * dto.getDaysRented();


        return rentalRepository.save(new RentalsModel(dto, data, priceTotal, customer, game));
    }
}
