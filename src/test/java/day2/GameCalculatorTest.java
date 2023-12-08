package day2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.BadInputException;
import utils.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GameCalculatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findGamesPossibleInputIsNullTest() throws BadInputException {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertNull(gameCalculator.findGamesPossible( null));
    }

    @Test
    void findGamesPossibleBagInputIsEmptyTest() throws BadInputException {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertNull(gameCalculator.findGamesPossible(List.of()));
    }

    @Test
    void findGamesPossibleIncorrectInputStringThrowsErrorTest() {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertThrows(BadInputException.class,
                () -> gameCalculator.findGamesPossible(List.of("Game 1: 3 yellow, 4 red: 1 red, 2 green, 6 blue; 2 green")),
                CommonConstants.invalidGameString);

    }

    @Test
    void findGamesPossibleIncorrectGameIdThrowsErrorTest() {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertThrows(BadInputException.class,
                () -> gameCalculator.findGamesPossible(List.of("UUU XXX 1: 3 green, 4 red; 1 red, 2 green, 6 blue; 2 green")),
                CommonConstants.invalidGameId);

    }

    @Test
    void findGamesPossibleGameIdNonNumericThrowsErrorTest() {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertThrows(BadInputException.class,
                () -> gameCalculator.findGamesPossible(List.of("Game VVV: 3 yellow, 4 red; 1 red, 2 green, 6 blue; 2 green")),
                CommonConstants.invalidGameString);

    }

    @Test
    void findGamesPossibleIncorrectBallColourInputThrowsErrorTest() {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act Assert
        assertThrows(BadInputException.class,
                () -> gameCalculator.findGamesPossible(List.of("Game 1: 3 yellow, 4 red; 1 red, 2 green, 6 blue; 2 green")),
                CommonConstants.invalidCubeColour);

    }

    @Test
    void findGamesPossibleValidInputTest() throws BadInputException {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act
        GameResult gameResult = gameCalculator.findGamesPossible(List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        ));

        // Assert
        assertEquals(gameResult.getGamesPossible().size(), 1);
        assertEquals(gameResult.getSumOfGames(), 1);
    }

    @Test
    void findGamesPossibleValidMultipleInputTest() throws BadInputException {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();

        // Act
        GameResult gameResult = gameCalculator.findGamesPossible(List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        ));

        // Assert
        assertEquals(gameResult.getGamesPossible().size(), 3);
        assertTrue(gameResult.getGamesPossible().stream().map(Game::getGameId).toList().containsAll(List.of(1, 2, 5)));
        assertFalse(gameResult.getGamesPossible().stream().map(Game::getGameId).toList().containsAll(List.of(3, 4)));
        assertEquals(gameResult.getSumOfGames(), 8);
    }

    @Test
    void findGamesPossibleValidFileInputTest() throws BadInputException {
        // Arrange
        GameCalculator gameCalculator = new GameCalculator();
        CommonUtils utils = new CommonUtils();
        List<String> gameInput = utils.readGameData();

        // Act
        GameResult gameResult = gameCalculator.findGamesPossible(gameInput);

        // Assert
        assertEquals(gameResult.getGamesPossible().size(), 4);
        assertTrue(gameResult.getGamesPossible().stream().map(Game::getGameId).toList().containsAll(List.of(1, 16, 41, 98)));
        assertFalse(gameResult.getGamesPossible().stream().map(Game::getGameId).toList().containsAll(List.of(3, 4)));
        assertEquals(gameResult.getSumOfGames(), 156);
    }
}