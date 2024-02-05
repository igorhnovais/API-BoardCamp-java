package com.api.boardcamp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.boardcamp.dtos.GameDTO;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.services.GameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
public class GameController {
    final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> postGame(@RequestBody @Valid GameDTO body) {
        GameModel game = gameService.saveNewGame(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}
