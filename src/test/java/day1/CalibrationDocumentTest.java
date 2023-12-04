package day1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CommonUtils;

import static org.junit.jupiter.api.Assertions.*;

class CalibrationDocumentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void computeCalibrationValueEmptyInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument();

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 0);
    }

    @Test
    void computeCalibrationValueEmptyStringInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 0);
    }

    @Test
    void computeCalibrationValueOnlyAlphabeticInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("sssssssssssssssssssssss");
        calibrationDocument.addInput("abc");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 0);
    }

    @Test
    void computeCalibrationValueOnlyNumericStringInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("2323232");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 22);
    }

    @Test
    void computeCalibrationValueOnlyNumericStringMultiLineInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("2323232");
        calibrationDocument.addInput("3645784");
        calibrationDocument.addInput("8764578643");
        calibrationDocument.addInput("00000000");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 139);
    }

    @Test
    void computeCalibrationValueOnlySingleNumberInStringInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("5");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 55);
    }

    @Test
    void computeCalibrationValueAlphaNumericInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("j6hgsd323asd");


        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 63);
    }

    @Test
    void computeCalibrationValueAlphaNumericMultiLineInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("1abc2");
        calibrationDocument.addInput("pqr3stu8vwx");
        calibrationDocument.addInput("a1b2c3d4e5f");
        calibrationDocument.addInput("treb7uchet");

        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 142);
    }

    @Test
    void computeCalibrationValueAlphaNumericNumberInWordsInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument("slconeightfoureight557m38");
        calibrationDocument.addInput("csdeightwobtsxflml4eightbxbzmvhq2four");


        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 42);
    }

    @Test
    void computeCalibrationValueFileInput() {
        // Arrange
        CalibrationDocument calibrationDocument = new CalibrationDocument();
        CommonUtils utils = new CommonUtils();
        calibrationDocument.getCalibrationInput().addAll(utils.readElfMapData());


        // Act Assert
        Assertions.assertEquals(calibrationDocument.computeCalibrationValue(), 53794);
    }



}