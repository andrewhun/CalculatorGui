/*
* This file contains the CalculationHandler class. It is responsible for carrying out
* operations according to the user's instructions. The goal of creating this class (and
* the DisplayHandler class) was to reduce the overall complexity of the application by
* hiding lower-level implementation details.
 */
package com.andrewhun.calculator;

public class CalculationHandler {

    // Initializing class member data
    private static Double firstNumber = 0.0;
    private static Double secondNumber = 0.0;
    private static Double result = 0.0;

    // Getters, setters and reset functions for the member data
    public static Double getFirstNumber() {

        return firstNumber;
    }

    public static void setFirstNumber(Double newNumber) {

        firstNumber = newNumber;
    }

    public static void resetFirstNumber() {

        firstNumber = 0.0;
    }

    public static Double getSecondNumber() {

        return secondNumber;
    }

    public static void setSecondNumber(Double newNumber) {

        secondNumber = newNumber;
    }

    public static void resetSecondNumber() {

        secondNumber = 0.0;
    }

    public static Double getResult() {

        return result;
    }

    public static void setResult(Double testResult) {

        // Use this setter for testing purposes only
        result = testResult;
    }

    public static void resetResult() {

        result = 0.0;
    }

    // Calculate the result of the selected operation
    public static void executeOperation(Operation selectedOperation) {

        switch (selectedOperation) {

            case ADDITION:

                result = firstNumber + secondNumber;
                break;

            case SUBTRACTION:

                result = firstNumber - secondNumber;
                break;

            case MULTIPLICATION:

                result = firstNumber * secondNumber;
                break;

            case DIVISION:

                result = firstNumber / secondNumber;
                break;
        }

    }

    // Reset all variables
    public static void resetNumbers() {

        resetFirstNumber();
        resetSecondNumber();
        resetResult();
    }
}
