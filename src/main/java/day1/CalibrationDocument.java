package day1;

import java.util.*;
import java.util.stream.Collectors;

public class CalibrationDocument {

    List<String> calibrationInput = new ArrayList<>();

    List<String> words = Arrays.stream(new String []{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}).collect(Collectors.toList());

    CalibrationDocument() {}

    CalibrationDocument(String input) {
        calibrationInput.add(input);
    }

    public void addInput(final String input) {
        this.calibrationInput.add(input);
    }

    public Integer computeCalibrationValue() {
        if(this.calibrationInput.isEmpty()) return 0;
        Integer sum = 0;
        for (String input: calibrationInput) {
            input = decodeInput(input);
            Integer firstNum = getCalibrationValue(input);
            Integer secondNum = getCalibrationValue(new StringBuilder(input).reverse().toString());
            Integer finalNum = (firstNum * 10)  + secondNum;
            sum = sum + finalNum;
        }
        return sum;
    }

    private Integer getCalibrationValue(String input) {
        if(input == null || input.isEmpty() || input.isBlank()) return 0;
        char[] inputArr = input.toCharArray();
        int num = 0;
        for (char c : inputArr) {
            if (Character.isDigit(c)) {
                num = Integer.parseInt(String.valueOf(c));
                break;
            }
        }
        return num;
    }

    private String decodeInput(String input) {
        if(input == null || input.isBlank() || input.isBlank()) return input;
        for(String key: words) {
            if(input.contains(key))  {
                input = input.replaceAll(key, String.valueOf(words.indexOf(key)));
            }
        }
        return input;
    }

    public List<String> getCalibrationInput() {
        return calibrationInput;
    }

    public void setCalibrationInput(List<String> calibrationInput) {
        this.calibrationInput = calibrationInput;
    }
}
