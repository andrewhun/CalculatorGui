/*
* This file contains test cases for the DisplayHandler class.
*/
package com.andrewhun.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DisplayHandlerTestCase {

    // Test that the setter, getter and reset methods for the displayedNumberString variable work
    @Test
    void TestDisplayedNumberGetterSetterAndReset() {

       DisplayHandler.setDisplayedNumberString("6");
       Assertions.assertEquals("6", DisplayHandler.getDisplayedNumberString());

       DisplayHandler.resetDisplayedNumberString();
       Assertions.assertEquals("0", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the setter, getter and reset methods for the topDisplayString variable work
    @Test
    void TestTopDisplayGetterSetterAndReset() {

        DisplayHandler.setTopDisplayString("6 + ");
        Assertions.assertEquals("6 + ", DisplayHandler.getTopDisplayString());

        DisplayHandler.resetTopDisplayString();
        Assertions.assertEquals("", DisplayHandler.getTopDisplayString());
    }

    // Test appending the selected digit to the display
    @Test
    void TestAppendDigit() {

        DisplayHandler.setDisplayedNumberString("4");
        DisplayHandler.appendDigit("5");
        Assertions.assertEquals("45", DisplayHandler.getDisplayedNumberString());
    }

    // Test adding a new number to the display
    @Test
    void TestReplaceNumber() {

        DisplayHandler.replaceNumber("9");
        Assertions.assertEquals("9", DisplayHandler.getDisplayedNumberString());
    }

    // Test showing an error message to the user
    @Test
    void TestShowErrorMessage() {

        DisplayHandler.showErrorMessage("Test error message.");
        Assertions.assertEquals("Test error message.", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the program can tell if there is only one digit on the display
    @Test
    void TestIsLastDigit() {

        // It should be true when the default value is displayed
        DisplayHandler.resetDisplayedNumberString();
        Assertions.assertTrue(DisplayHandler.isLastDigit());

        //It should also be true if there's only a zero with a decimal point on the display
        DisplayHandler.setDisplayedNumberString("0.");
        Assertions.assertTrue(DisplayHandler.isLastDigit());

        // It should be false otherwise
        DisplayHandler.setDisplayedNumberString("1.");
        Assertions.assertFalse(DisplayHandler.isLastDigit());
    }

    // Test deleting a digit (or decimal point)
    @Test
    void TestDeleteDigit() {

        DisplayHandler.setDisplayedNumberString("15.5");

        DisplayHandler.deleteDigit();
        Assertions.assertEquals("15.", DisplayHandler.getDisplayedNumberString());

        DisplayHandler.deleteDigit();
        Assertions.assertEquals("15", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the program can tell when there is only a minus sign displayed
    @Test
    void TestOnlyMinusSignIsDisplayed() {

        DisplayHandler.setDisplayedNumberString("-5");
        Assertions.assertFalse(DisplayHandler.onlyMinusSignIsDisplayed());

        DisplayHandler.deleteDigit();
        Assertions.assertTrue(DisplayHandler.onlyMinusSignIsDisplayed());
    }

    // Test that the program can tell if the displayed number is negative
    @Test
    void TestNumberIsNegative() {

        DisplayHandler.setDisplayedNumberString("-5");
        Assertions.assertTrue(DisplayHandler.numberIsNegative());

        DisplayHandler.setDisplayedNumberString("5");
        Assertions.assertFalse(DisplayHandler.numberIsNegative());
    }

    // Test adding a minus sign to the displayed number
    @Test
    void TestAddMinusSign() {

        DisplayHandler.setDisplayedNumberString("10");
        DisplayHandler.addMinusSign();
        Assertions.assertEquals("-10", DisplayHandler.getDisplayedNumberString());
    }

    // Test removing the minus sign from the displayed number
    @Test
    void TestRemoveMinusSign() {

        DisplayHandler.setDisplayedNumberString("-10");
        DisplayHandler.removeMinusSign();
        Assertions.assertEquals("10", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the program can tell if the displayed number has a decimal point
    @Test
    void TestHasNoDecimalPoint() {

        DisplayHandler.setDisplayedNumberString("2");
        Assertions.assertTrue(DisplayHandler.hasNoDecimalPoint());

        DisplayHandler.setDisplayedNumberString("2.5");
        Assertions.assertFalse(DisplayHandler.hasNoDecimalPoint());
    }

    // Test adding a decimal point to the displayed number
    @Test
    void TestAddDecimalPoint() {

        DisplayHandler.setDisplayedNumberString("3");
        DisplayHandler.addDecimalPoint();
        Assertions.assertEquals("3.", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the selected operation is displayed correctly
    @Test
    void TestAddOperationToDisplay() {

        // Empty the top display to make sure there are no unwanted elements on it
        DisplayHandler.resetTopDisplayString();

        // Test that the program handles "0." inputs
        DisplayHandler.setDisplayedNumberString("0.");
        DisplayHandler.addOperationToDisplay(Operation.ADDITION);
        Assertions.assertEquals("0 + ", DisplayHandler.getTopDisplayString());
        DisplayHandler.resetTopDisplayString();

        // Test all 4 operations
        DisplayHandler.setDisplayedNumberString("5");

        DisplayHandler.addOperationToDisplay(Operation.ADDITION);
        Assertions.assertEquals("5 + ", DisplayHandler.getTopDisplayString());
        DisplayHandler.resetTopDisplayString();

        DisplayHandler.addOperationToDisplay(Operation.SUBTRACTION);
        Assertions.assertEquals("5 - ", DisplayHandler.getTopDisplayString());
        DisplayHandler.resetTopDisplayString();

        DisplayHandler.addOperationToDisplay(Operation.MULTIPLICATION);
        Assertions.assertEquals("5 * ", DisplayHandler.getTopDisplayString());
        DisplayHandler.resetTopDisplayString();

        DisplayHandler.addOperationToDisplay(Operation.DIVISION);
        Assertions.assertEquals("5 / ", DisplayHandler.getTopDisplayString());
        DisplayHandler.resetTopDisplayString();
    }

    // Test that the displayed operation is changed according to the user's input
    @Test
    void TestChangeDisplayedOperation() {

        // Set up the display
        DisplayHandler.setTopDisplayString("2 / ");

        // Test all 4 operations
        DisplayHandler.changeDisplayedOperation(Operation.ADDITION);
        Assertions.assertEquals("2 + ", DisplayHandler.getTopDisplayString());

        DisplayHandler.changeDisplayedOperation(Operation.SUBTRACTION);
        Assertions.assertEquals("2 - ", DisplayHandler.getTopDisplayString());

        DisplayHandler.changeDisplayedOperation(Operation.MULTIPLICATION);
        Assertions.assertEquals("2 * ", DisplayHandler.getTopDisplayString());

        DisplayHandler.changeDisplayedOperation(Operation.DIVISION);
        Assertions.assertEquals("2 / ", DisplayHandler.getTopDisplayString());
    }

    // Test that the program can tell if the top display is empty
    @Test
    void TestTopDisplayIsNotEmpty() {

        DisplayHandler.setTopDisplayString("Not empty!");
        Assertions.assertTrue(DisplayHandler.topDisplayIsNotEmpty());

        DisplayHandler.resetTopDisplayString();
        Assertions.assertFalse(DisplayHandler.topDisplayIsNotEmpty());
    }

    // Test that the program can tell if the current number is zero (used for division by zero errors)
    @Test
    void TestDenominatorIsZero() {

        // Test for the "0." value, which is also an equivalent of zero
        DisplayHandler.setDisplayedNumberString("0.");
        Assertions.assertTrue(DisplayHandler.denominatorIsZero());

        // Test for 0
        DisplayHandler.resetDisplayedNumberString();
        Assertions.assertTrue(DisplayHandler.denominatorIsZero());

        // Test for non-zero
        DisplayHandler.setDisplayedNumberString("100");
        Assertions.assertFalse(DisplayHandler.denominatorIsZero());
    }

    // Test that the program can tell if there is a "division by zero" error message displayed
    @Test
    void TestErrorFreeCalculation() {

        DisplayHandler.setDisplayedNumberString("5");
        Assertions.assertTrue(DisplayHandler.errorFreeCalculation());

        DisplayHandler.setDisplayedNumberString("Error: Division by zero");
        Assertions.assertFalse(DisplayHandler.errorFreeCalculation());
    }

    // Test that the program displays the result correctly
    @Test
    void TestShowResult() {

        // Test that needless decimal values are hidden from the user
        DisplayHandler.showResult("5.0");
        Assertions.assertEquals("5", DisplayHandler.getDisplayedNumberString());

        // Test that actual decimal values are displayed to the user
        DisplayHandler.showResult("5.15");
        Assertions.assertEquals("5.15", DisplayHandler.getDisplayedNumberString());
    }
}
