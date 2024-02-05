package com.api.boardcamp.models;

import com.api.boardcamp.dtos.RentalsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")

public class RentalsModel {

    public RentalsModel(RentalsDTO dto, String date, int priceTotal, CustomerModel customer, GameModel game){
        this.rentDate = date;
        this.daysRented = dto.getDaysRented();
        this.returnDate = null;
        this.originalPrice = priceTotal;
        this.delayFree = 0;
        this.customer = customer;
        this.game = game;
    }

    public RentalsModel(RentalsModel rental, String dateReturn, int priceDelay, GameModel game){
        this.id = rental.getId();
        this.rentDate = rental.getRentDate();
        this.daysRented = rental.getDaysRented();
        this.returnDate = dateReturn;
        this.originalPrice = rental.getOriginalPrice();
        this.delayFree = priceDelay;
        this.customer = rental.getCustomer();
        this.game = game;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String rentDate;

    @Column(nullable = false)
    private int daysRented;

    @Column(nullable = true)
    private String returnDate;

    @Column(nullable = false)
    private int originalPrice;

    @Column(nullable = false)
    private int delayFree;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;
}
