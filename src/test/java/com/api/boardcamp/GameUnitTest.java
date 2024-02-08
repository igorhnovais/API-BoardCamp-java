package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.dtos.GameDTO;
import com.api.boardcamp.exceptions.ConflictException;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.services.GameService;

@SpringBootTest
public class GameUnitTest {
    @InjectMocks
    GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void repeatGameName_whenAddGame_thenThrowsConflictError(){
        GameDTO gameDto = new GameDTO("name", "image", 3, 1500);
        doReturn(true).when(gameRepository).existsByName(any());

        ConflictException exception = assertThrows(ConflictException.class, () -> gameService.saveNewGame(gameDto));

        assertNotNull(exception);
        assertEquals("the game already exists!", exception.getMessage());
		verify(gameRepository, times(0)).save(any());
		verify(gameRepository, times(1)).existsByName(any());
    }
}
