package day2;

import java.util.List;

public class GameResult {
    private List<Game> gamesPossible;
    private Integer sumOfGames;

    public List<Game> getGamesPossible() {
        return gamesPossible;
    }

    public void setGamesPossible(List<Game> gamesPossible) {
        this.gamesPossible = gamesPossible;
    }

    public Integer getSumOfGames() {
        return sumOfGames;
    }

    public void setSumOfGames(Integer sumOfGames) {
        this.sumOfGames = sumOfGames;
    }
}
