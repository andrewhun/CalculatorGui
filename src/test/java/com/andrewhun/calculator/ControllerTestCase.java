/*
* This class contains the unit tests for the Controller class. These tests rely on the
* TestFX library. I have tried other methods, but this is the one that works reliably and
* with relatively little overhead.
*
* You may notice that these tests resemble the
* integration tests (MainTestCase). They are similar, but with different focus. This class
* focuses on the internal working of the Controller class (especially focusing on the status
* variables, as well as the variables of the DisplayHandler and CalculationHandler classes).
 */
package com.andrewhun.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ControllerTestCase extends TestFXBaseClass {

    private Controller controller;
    private Double ZERO = 0.0;

    // Test that the getter and setter for the selectedOperation variable work
    @Test
    void TestGetSetSelectedOperation() {

        controller = Main.getController();

        controller.setSelectedOperation(Operation.DIVISION);
        Assertions.assertEquals(Operation.DIVISION, controller.getSelectedOperation());
    }

    // Test that the getter and setter for the displayState variable work
    @Test
    void TestGetSetDisplayState() {

        controller = Main.getController();

        controller.setDisplayState(DisplayState.CHANGED);
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());
    }

    // Test that the default variable values are as expected
    @Test
    void TestDefaultValues() {

        controller = Main.getController();

        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }

    // Test that the Controller can handle adding digits to the display
    @Test
    void TestSelectActionForDigits() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());
        Assertions.assertEquals("1", DisplayHandler.getDisplayedNumberString());

        clickOn(ZERO_BUTTON_ID_TAG);
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());
        Assertions.assertEquals("10", DisplayHandler.getDisplayedNumberString());
    }

    // Test that the Controller can handle deleting values from the display
    @Test
    void TestSelectActionForBackspace() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);

        clickOn(BACKSPACE_BUTTON_ID_TAG);
        Assertions.assertEquals("1", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());

        clickOn(BACKSPACE_BUTTON_ID_TAG);
        Assertions.assertEquals("0", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());

        clickOn(BACKSPACE_BUTTON_ID_TAG);
        Assertions.assertEquals("0", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
    }

    // Test that the Controller can handle changing the sign of the displayed number
    @Test
    void TestChangeSignOfNumber() {

        controller = Main.getController();

        final String PLUS_MINUS_BUTTON_ID_TAG = "#plusMinus";

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);

        Assertions.assertEquals("-10", DisplayHandler.getDisplayedNumberString());

        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        Assertions.assertEquals("10", DisplayHandler.getDisplayedNumberString());

        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        Assertions.assertEquals("-", DisplayHandler.getDisplayedNumberString());

        // Check for the reaction to the case when only the minus sign is displayed and the backspace is pressed
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        Assertions.assertEquals("0", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
    }

    // Test that the Controller can handle adding a decimal point to the displayed number
    @Test
    void TestAddDecimalPoint() {

        controller = Main.getController();

        clickOn(DECIMAL_POINT_ID_TAG);
        Assertions.assertEquals("0.", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());

        // A second decimal point should not be added
        clickOn(DECIMAL_POINT_ID_TAG);
        Assertions.assertEquals("0.", DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());
    }

    // Test that the Controller can handle initiating addition operations
    @Test
    void TestInitiateAddition() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        // Test that the display variables contain the correct information
        Assertions.assertEquals("1 + ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());

        // Test that the status variables indicate the state of the calculator correctly
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.ADDITION, controller.getSelectedOperation());

        // Test that the first number is stored by the program
        Assertions.assertEquals((Double) 1.0, CalculationHandler.getFirstNumber());
    }

    // Test that the Controller can handle initiating subtraction operations
    @Test
    void TestInitiateSubtraction() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);

        // Test that the display variables contain the correct information
        Assertions.assertEquals("1.1 - ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());

        // Test that the status variables indicate the state of the calculator correctly
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.SUBTRACTION, controller.getSelectedOperation());

        // Test that the first number is stored by the program
        Assertions.assertEquals((Double) 1.1, CalculationHandler.getFirstNumber());
    }

    // Test that the Controller can handle initiating multiplication operations
    @Test
    void TestInitiateMultiplication() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ASTERISK_BUTTON_ID_TAG);

        // Test that the display variables contain the correct information
        Assertions.assertEquals("10 * ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());

        // Test that the status variables indicate the state of the calculator correctly
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.MULTIPLICATION, controller.getSelectedOperation());

        // Test that the first number is stored by the program
        Assertions.assertEquals((Double) 10.0, CalculationHandler.getFirstNumber());
    }

    // Test that the Controller can handle initiating division operations
    @Test
    void TestInitiateDivision() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        // Test that the display variables contain the correct information
        Assertions.assertEquals("100 / ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());

        // Test that the status variables indicate the state of the calculator correctly
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.DIVISION, controller.getSelectedOperation());

        // Test that the first number is stored by the program
        Assertions.assertEquals((Double) 100.0, CalculationHandler.getFirstNumber());
    }

    // Test that the Controller can handle changing operations
    @Test
    void TestChangeOperation() {

        controller = Main.getController();

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        // Addition
        Assertions.assertEquals("11 + ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(Operation.ADDITION, controller.getSelectedOperation());

        // Subtraction
        clickOn(MINUS_BUTTON_ID_TAG);
        Assertions.assertEquals("11 - ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(Operation.SUBTRACTION, controller.getSelectedOperation());

        // Multiplication
        clickOn(ASTERISK_BUTTON_ID_TAG);
        Assertions.assertEquals("11 * ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(Operation.MULTIPLICATION, controller.getSelectedOperation());

        // Division
        clickOn(SLASH_BUTTON_ID_TAG);
        Assertions.assertEquals("11 / ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(Operation.DIVISION, controller.getSelectedOperation());

        // Addition again
        clickOn(PLUS_BUTTON_ID_TAG);
        Assertions.assertEquals("11 + ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(Operation.ADDITION, controller.getSelectedOperation());

        // Test that the rest of the variables are as expected
        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());
        Assertions.assertEquals((Double) 11.0, CalculationHandler.getFirstNumber());
    }

    // Test that the Controller executes operations in the background
    @Test
    void TestExecuteOperation() {

        controller = Main.getController();

        // 11 - 1 +
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        // Test that the correct information is displayed
        Assertions.assertEquals("11 - 1 + ", DisplayHandler.getTopDisplayString());

        // Test that the calculation gets carried out
        Assertions.assertEquals((Double) 1.0, CalculationHandler.getSecondNumber());
        Assertions.assertEquals((Double) 10.0, CalculationHandler.getResult());

        // Test that the result is stored as the first number of the next operation
        Assertions.assertEquals((Double) 10.0, CalculationHandler.getFirstNumber());

        // Test that the calculator knows which operation is next
        Assertions.assertEquals(Operation.ADDITION, controller.getSelectedOperation());
    }

    // Test that the Controller can handle chains of operations
    @Test
    void TestChainingOperations() {

        controller = Main.getController();
        // 101 - 1 + 0.1 *
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ASTERISK_BUTTON_ID_TAG);

        // Test that the correct information is displayed
        Assertions.assertEquals("101 - 1 + 0.1 * ", DisplayHandler.getTopDisplayString());

        // Test that the calculator keeps track of the numbers correctly
        Assertions.assertEquals((Double) 0.1, CalculationHandler.getSecondNumber());
        Assertions.assertEquals((Double) 100.1, CalculationHandler.getResult());
        Assertions.assertEquals((Double) 100.1, CalculationHandler.getFirstNumber());

        // Test that the calculator knows which operation is next
        Assertions.assertEquals(Operation.MULTIPLICATION, controller.getSelectedOperation());
    }

    // Test that the Controller can handle calculating results
    @Test
    void TestCalculateResult() {

        controller = Main.getController();

        // 110 / 11 =
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        // Test that the result is displayed and the calculator is reset
        Assertions.assertEquals("10", DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());

        // Test that the first number is displayed if no operation is selected
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        Assertions.assertEquals("1.01", DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());

        // Test that the first number is displayed if no second number is added

        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        Assertions.assertEquals("0.11", DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }

    // Test that the Controller can handle clearing the calculator
    @Test
    void TestClearCalculator() {

        controller = Main.getController();

        // 111 - 11 * on the top display, 10 on the bottom
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ASTERISK_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);

        // Test that values are stored as expected
        Assertions.assertEquals("111 - 11 * ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals("10", DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(DisplayState.CHANGED, controller.getDisplayState());
        Assertions.assertEquals(Operation.MULTIPLICATION, controller.getSelectedOperation());

        Assertions.assertEquals((Double) 11.0, CalculationHandler.getSecondNumber());
        Assertions.assertEquals((Double) 100.0, CalculationHandler.getResult());
        Assertions.assertEquals((Double) 100.0, CalculationHandler.getFirstNumber());

        clickOn(CLEAR_BUTTON_ID_TAG);

        // Test that all values are reset
        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(DEFAULT_DISPLAY_VALUE, DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }

    // Test that the Controller can handle division by zero errors
    @Test
    void TestDivisionByZero() {

        final String ERROR_MESSAGE = "Error: Division by zero";
        controller = Main.getController();

        // Test the case when there is a zero-division in the operation chain
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        // An error message should be displayed and the calculator should be reset
        Assertions.assertEquals(ERROR_MESSAGE, DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());

        // Test the case when the user directly tries to divide by zero (by pressing the equals button)
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        // The same thing should happen as in the other case
        Assertions.assertEquals(ERROR_MESSAGE, DisplayHandler.getDisplayedNumberString());

        Assertions.assertEquals(EMPTY, DisplayHandler.getTopDisplayString());

        Assertions.assertEquals(DisplayState.DEFAULT, controller.getDisplayState());
        Assertions.assertEquals(Operation.NONE, controller.getSelectedOperation());

        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getSecondNumber());
        Assertions.assertEquals(ZERO, CalculationHandler.getResult());
    }

    // Test that the Controller can handle "0." inputs
    @Test
    void TestZeroIsStoredCorrectly() {

        controller = Main.getController();

        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        // This input should be treated the same is "normal" zero inputs
        Assertions.assertEquals("0 + ", DisplayHandler.getTopDisplayString());
        Assertions.assertEquals(ZERO, CalculationHandler.getFirstNumber());
    }
}
