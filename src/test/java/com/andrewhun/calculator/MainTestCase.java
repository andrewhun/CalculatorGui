/*
* This file contains the integration tests for the CalculatorGui application.
 */
package com.andrewhun.calculator;

import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class MainTestCase extends TestFXBaseClass {

    // Test that adding new numbers to the display works as intended
    @Test
    void ensureThatDefaultNumberIsReplaced() {

        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
        clickOn(ONE_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1"));
    }

    // Test that appending digits to the displayed number works as intended
    @Test
    void ensureThatSelectedDigitIsAppended() {

       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(ZERO_BUTTON_ID_TAG);
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("10"));
    }

    // Test that deleting digits from the display works as intended
    @Test
    void ensureThatDeletingDigitsWorks() {

        // pressing backspace when the default value is displayed should have no effect
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

        // deleting the single digit from the display should mean its replaced by the default value
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

        // Deleting one digit from a number with multiple digits should just do that
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);

        clickOn(BACKSPACE_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("10"));

        clickOn(BACKSPACE_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1"));
    }

    // Test that changing the sign of numbers works as intended
    @Test
    void ensureThatChangingSignsWorks() {

       final String PLUS_MINUS_BUTTON_ID_TAG = "#plusMinus";

        // Nothing should happen if the user presses this button before starting to type
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

        // Positive numbers should get a minus sign when the button is pressed
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("-1"));

        // Negative numbers should lose their minus sign when the button is pressed
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1"));

        /* If the number from behind the minus sign is deleted, pressing the button should result in
        the default value being displayed again*/
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);
        clickOn(BACKSPACE_BUTTON_ID_TAG);
        clickOn(PLUS_MINUS_BUTTON_ID_TAG);

        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that adding decimal points to the displayed number works as intended
    @Test
    void ensureThatAddingDecimalPointsWorks() {

        // Pressing the button once should add a decimal point to the displayed number
        clickOn(DECIMAL_POINT_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("0."));

        // Clicking the button a second time should do nothing
        clickOn(DECIMAL_POINT_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("0."));
    }

    // Test that initiating an addition works as intended
    @Test
    void ensureThatPlusButtonWorks() {

       clickOn(PLUS_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("0 + "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that initiating a subtraction works as intended
    @Test
    void ensureThatMinusButtonWorks() {

       clickOn(MINUS_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("0 - "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that initiating a multiplication works as intended
    @Test
    void ensureThatAsteriskButtonWorks() {

       clickOn(ASTERISK_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("0 * "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that initiating a division works as intended
    @Test
    void ensureThatSlashButtonWorks() {

       clickOn(SLASH_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("0 / "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that if the user inputs "0." it is displayed as 0 in calculations
    @Test
    void ensureThatZeroIsDisplayedCorrectly() {

       clickOn(DECIMAL_POINT_ID_TAG);
       clickOn(PLUS_BUTTON_ID_TAG);

       verifyThat(TOP_DISPLAY_ID_TAG, hasText("0 + "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that users can change operations if they want
    @Test
    void ensureThatOperationChangingWorks() {

       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(PLUS_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("1 + "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

       clickOn(MINUS_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("1 - "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

       clickOn(ASTERISK_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("1 * "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));

       clickOn(SLASH_BUTTON_ID_TAG);
       verifyThat(TOP_DISPLAY_ID_TAG, hasText("1 / "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that users can chain multiple operations in a single calculation
    @Test
    void ensureThatChainingOperationsWorks() {

       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(ZERO_BUTTON_ID_TAG);
       clickOn(PLUS_BUTTON_ID_TAG);

       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(ASTERISK_BUTTON_ID_TAG);

       verifyThat(TOP_DISPLAY_ID_TAG, hasText("10 + 1 * "));
       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that the equals button works as intended
    @Test
    void ensureThatResultsAreCorrect() {



        // If the user presses the equals button without doing anything else the display shouldn't change
        clickOn(EQUALS_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
        verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));

        // Decimal values should also be displayed
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1.10"));

        // If the user does not enter a second number, the first number should be displayed as the result
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1"));
        verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));

        // Calculations should be accurate and he correct result should be displayed (11 - 1 = 10)
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("10"));
        verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));

        // Multiple operations should also yield correct results ( (100 + 10) / 10 = 11)
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(EQUALS_BUTTON_ID_TAG);

        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("11"));
        verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));
    }

    // Test that clearing the calculator works as intended
    @Test
    void ensureThatClearingWorks() {

        // Populate the display (10 + 11 * 1.1 - 100 / and 1.01 on the display)
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(PLUS_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ASTERISK_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(MINUS_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(SLASH_BUTTON_ID_TAG);

        clickOn(ONE_BUTTON_ID_TAG);
        clickOn(DECIMAL_POINT_ID_TAG);
        clickOn(ZERO_BUTTON_ID_TAG);
        clickOn(ONE_BUTTON_ID_TAG);

        verifyThat(TOP_DISPLAY_ID_TAG, hasText("10 + 11 * 1.1 - 100 / "));
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText("1.01"));

        // the Clear button should erase all information from the display
        clickOn(CLEAR_BUTTON_ID_TAG);

        verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));
        verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(DEFAULT_DISPLAY_VALUE));
    }

    // Test that division by zero errors are caught
    @Test
    void ensureThatDivisionByZeroIsCaught() {

       final String ERROR_MESSAGE = "Error: Division by zero";
       // Check that division by zero errors are caught when the user presses the equals button
       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(SLASH_BUTTON_ID_TAG);
       clickOn(ZERO_BUTTON_ID_TAG);
       clickOn(EQUALS_BUTTON_ID_TAG);

       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(ERROR_MESSAGE));
       verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));

       // Check that division by zero errors are caught in the middle of a chain of operations
       clickOn(ONE_BUTTON_ID_TAG);
       clickOn(SLASH_BUTTON_ID_TAG);
       clickOn(ZERO_BUTTON_ID_TAG);
       clickOn(PLUS_BUTTON_ID_TAG);

       verifyThat(DISPLAYED_NUMBER_ID_TAG, hasText(ERROR_MESSAGE));
       verifyThat(TOP_DISPLAY_ID_TAG, hasText(EMPTY));
    }
}
