package utils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public List<String> readElfMapData() {
        List<String> calibrationInput = new ArrayList<>();
        URL url = getClass().getResource("/elfmap-day1-input.txt");
        try {
            assert url != null;
            try(BufferedReader br = new BufferedReader(new FileReader(url.getPath()))) {
                String line = br.readLine();
                while (line != null) {
                    calibrationInput.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return calibrationInput;
    }

    public List<String> readGameData() {
        List<String> gameInput = new ArrayList<>();
        URL url = getClass().getResource("/game-day2-input.txt");
        try {
            assert url != null;
            try(BufferedReader br = new BufferedReader(new FileReader(url.getPath()))) {
                String line = br.readLine();
                while (line != null) {
                    gameInput.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gameInput;
    }
}
