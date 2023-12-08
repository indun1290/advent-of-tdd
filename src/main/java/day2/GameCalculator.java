package day2;

import utils.BadInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GameCalculator {

    private Cube[] cubesInBag = new Cube[3];
    GameCalculator() {
        cubesInBag[0] = new Cube(Colour.Red, 12);
        cubesInBag[1] = new Cube(Colour.Green, 13);
        cubesInBag[2] = new Cube(Colour.Blue, 14);
    }

    /**
     * This method takes a String list parameter to games and outputs it in form of
     * list of Game objects.
     * @param input
     * @return
     * @throws BadInputException
     */
    private List<Game> transformStringGamesToObject(List<String> input) throws BadInputException {
        if(input == null || input.isEmpty()) return null;
        List<Game> gamesList = new ArrayList<>();

        for(String gameStr: input) {
            String[] game = gameStr.split(": ");
            if(game.length != 2) {
                throw new BadInputException(CommonConstants.invalidGameString);
            }
            String [] gameId = game[0].split(" ");
            if(gameId.length != 2) {
                throw new BadInputException(CommonConstants.invalidGameId);
            }
            if(!gameId[0].equalsIgnoreCase("Game") || !gameId[1].chars().allMatch(Character::isDigit)) {
                throw new BadInputException(CommonConstants.invalidGameId);
            }
            String[] cubes = game[1].split("; ");

            // create game dto at this point and set game id
            Game gameDTO = new Game();
            gameDTO.setGameId(Integer.parseInt(gameId[1]));

            // create cube dto list at this point.
            List<Cube> cubesList = new ArrayList<>();

            for(String cube: cubes) {
                String [] colours = cube.split(", ");
                if(colours.length == 0) throw new BadInputException(CommonConstants.invalidCubeInput);


                for(String colour: colours) {
                    String [] c = colour.split(" ");
                    if(!c[0].chars().allMatch(Character::isDigit)) throw new BadInputException(CommonConstants.invalidCubeCountInput);
                    if(!checkIfColourIsValid(c[1])) throw new BadInputException(CommonConstants.invalidCubeColour + Stream.of(Colour.values()));

                    // now that all checks are complete, add this cube to cube list

                    cubesList.add(new Cube(getColour(c[1]), Integer.parseInt(c[0])));

                } //colours for end

                gameDTO.setCubes(cubesList);

            } // cubes for end

            gamesList.add(gameDTO);

        } // gameStr for end

        return gamesList;
    }

    private boolean checkIfColourIsValid(String colour) {
        return Stream.of(Colour.values()).anyMatch(v -> v.name().equalsIgnoreCase(colour));
    }

    private Colour getColour(String name) {
        return Stream.of(Colour.values()).filter(v -> v.name().equalsIgnoreCase(name)).findFirst().get();
    }


    public GameResult findGamesPossible(List<String> input) throws BadInputException {
        if(input== null) return null;
        List<Game> gameInput = transformStringGamesToObject(input);
        System.out.println(gameInput);
        List<Game> transformedGameInput = mergeCubesInputByColour(gameInput);
        System.out.println(transformedGameInput);
        GameResult gameResult = new GameResult();
        Integer gameSum = 0;
        if(transformedGameInput != null && !transformedGameInput.isEmpty()) {
            List<Game> gamesPossible = new ArrayList<>();
            for (Game game: transformedGameInput) {
                if(checkIfGameIsPossible(game.getCubes())) {
                    gamesPossible.add(game);
                    gameSum = gameSum + game.getGameId();
                }
            }
            gameResult.setGamesPossible(gamesPossible);
            gameResult.setSumOfGames(gameSum);
        }
        return gameResult;
    }

    private boolean checkIfGameIsPossible(List<Cube> cubes) {
        if(cubes != null && !cubes.isEmpty()) {
            return cubes.stream().filter(v -> v.getColour().equals(Colour.Red)).findFirst().get().getCount() <= cubesInBag[0].getCount() &&
                    cubes.stream().filter(v -> v.getColour().equals(Colour.Green)).findFirst().get().getCount() <= cubesInBag[1].getCount() &&
                    cubes.stream().filter(v -> v.getColour().equals(Colour.Blue)).findFirst().get().getCount() <= cubesInBag[2].getCount();
        }
        return false;
    }

    /**
     * This method is just for flattening cubes inside a game into three colours with their count.
     * It can be achieved through stream and grouping APIs but I didn't want to spend time in trying to
     * do that.
     * @param gameInput
     * @return
     */
    private List<Game> mergeCubesInputByColour(List<Game> gameInput) {
        if(gameInput != null && !gameInput.isEmpty()) {
            List<Game> newGameList = new ArrayList<>();
            for (Game game: gameInput) {
                Game newGame = new Game();
                newGame.setGameId(game.getGameId());
                List<Cube> cubeList = new ArrayList<>();
                Cube red = new Cube(Colour.Red, 0);
                Cube green = new Cube(Colour.Green, 0);
                Cube blue = new Cube(Colour.Blue, 0);
                for (Cube cube: game.getCubes()) {
                    switch (cube.getColour()) {
                        case Red -> red.setCount(red.getCount()+ cube.getCount());
                        case Green -> green.setCount(green.getCount()+cube.getCount());
                        case Blue -> blue.setCount(blue.getCount()+cube.getCount());
                        default -> throw new IllegalStateException("Unexpected value: " + cube.getColour());
                    }
                }
                cubeList.add(red); cubeList.add(green); cubeList.add(blue);
                newGame.setCubes(cubeList);
                newGameList.add(newGame);
            }
            return newGameList;
        }
        return null;
    }
}
