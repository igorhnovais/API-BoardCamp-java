package com.api.boardcamp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.GameDTO;
import com.api.boardcamp.exceptions.ConflictException;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.repositories.GameRepository;

@Service
public class GameService {
    
    final GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameModel saveNewGame(GameDTO dto) {
        boolean gameExists = gameRepository.existsByName(dto.getName());
        if (gameExists) {
            throw new ConflictException("the game already exists!");
        }

        GameModel game = new GameModel(dto);
        return gameRepository.save(game);
    }

    public List<GameModel> findAllGames() {
        return gameRepository.findAll();
    }
}
