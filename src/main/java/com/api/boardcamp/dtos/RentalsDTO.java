package com.api.boardcamp.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalsDTO {
    @NotNull(message = "Id user per day is required")
    @Positive(message = "Id user cannot be negative or zero")
    private Long customerId;

    @NotNull(message = "Id game is required")
    @Positive(message = "Id game cannot be negative or zero")
    private Long gameId;

    @NotNull(message = "days rented is required")
    @Positive(message = "days rented cannot be negative or zero")
    private Long daysRented;
}
