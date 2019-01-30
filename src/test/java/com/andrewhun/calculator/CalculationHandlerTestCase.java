/*
* This file contains test cases for the CalculationHandler class.
 */
package com.andrewhun.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculationHandlerTestCase {

    // Use this class member as a shorthand (instead of writing (Double) 0.0 multiple times)
    private final Double ZERO = 0.0;

    // Test that the getter, setter and reset methods work for the firstNumber variable
    @Test
    void TestFirstNumberGetSetReset() {

        CalculationHandler.setFirstNumber(6.0);
        Assertions.assertEquals((Double) 6.0, CalculationHandler.getFirstNumber());

        CalculationHandler.resetFirstNumber();
        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
    }

    // Test that the getter, setter and reset methods work for the secondNumber variable
    @Test
    void TestSecondNumberGetSetReset() {

        CalculationHandler.setSecondNumber(5.0);
        Assertions.assertEquals((Double) 5.0, CalculationHandler.getSecondNumber());

        CalculationHandler.resetSecondNumber();
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
    }

    // Test that the getter, setter and reset methods work for the result variable
    @Test
    void TestResultGetSetReset() {

        CalculationHandler.setResult(4.0);
        Assertions.assertEquals((Double) 4.0, CalculationHandler.getResult());

        CalculationHandler.resetResult();
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }

    // Test that the program calculates results correctly for all 4 operations
    @Test
    void TestExecuteOperation() {

        CalculationHandler.setFirstNumber(6.0);
        CalculationHandler.setSecondNumber(2.0);

        CalculationHandler.executeOperation(Operation.ADDITION);
        Assertions.assertEquals((Double) 8.0, CalculationHandler.getResult());
        CalculationHandler.resetResult();

        CalculationHandler.executeOperation(Operation.SUBTRACTION);
        Assertions.assertEquals((Double) 4.0, CalculationHandler.getResult());
        CalculationHandler.resetResult();

        CalculationHandler.executeOperation(Operation.MULTIPLICATION);
        Assertions.assertEquals((Double) 12.0, CalculationHandler.getResult());
        CalculationHandler.resetResult();

        CalculationHandler.executeOperation(Operation.DIVISION);
        Assertions.assertEquals((Double) 3.0, CalculationHandler.getResult());
        CalculationHandler.resetResult();
    }

    // Test that the main reset function resets all variables
    @Test
    void TestResetNumbers() {

        CalculationHandler.setFirstNumber(3.0);
        CalculationHandler.setSecondNumber(5.0);
        CalculationHandler.setResult(-2.0);

        CalculationHandler.resetNumbers();

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }
}
