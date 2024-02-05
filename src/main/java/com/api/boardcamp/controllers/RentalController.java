package com.api.boardcamp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.boardcamp.dtos.RentalsDTO;
import com.api.boardcamp.models.RentalsModel;
import com.api.boardcamp.services.RentalsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    
    final RentalsService rentalsService;

    RentalController(RentalsService rentalsService){
        this.rentalsService = rentalsService;
    }

    @PostMapping
    public ResponseEntity<Object> postRentals(@RequestBody @Valid RentalsDTO body){
        RentalsModel model = rentalsService.saveNewRental(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public List<RentalsModel> getRentals(){
        return rentalsService.findAllRentals();
    }
}
