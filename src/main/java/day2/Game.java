package day2;

import java.util.List;

public class Game {

    private Integer gameId;
    private List<Cube> cubes;

    Game() {

    }

    Game(List<Cube> cubes) {
        this.cubes = cubes;
    }

    public Game(Integer gameId, List<Cube> cubes) {
        this.gameId = gameId;
        this.cubes = cubes;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public void setCubes(List<Cube> cubes) {
        this.cubes = cubes;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", cubes=" + cubes +
                '}';
    }
}
