/*
*   This file contains the Controller class, which is the "brain" of this calculator app.
* It contains the logic of user-interactions. The general layout is the following:
* At the top of the file, a number of class variables are declared. These are used to
* monitor the state of the calculator. Enumerated types were used for this purpose in order
* to improve readability. These variables are then modified by the functions of the class,
* which are defined below the class variables.Different types of user interactions are handled by different functions.
* These functions interact with each other in some cases, by passing around data.
*
* The general layout of the program:
*   There are three classes that make up the backbone of this application: this Controller class, the DisplayHandler
* class and the CalculationHandler class. The Controller class controls the flow of logic in general, that is, it
* governs the reactions of the program to user actions (pressing buttons). The DisplayHandler class takes care of
* the string manipulation tasks that are necessary for displaying correct information to the user.
* The CalculationHandler class does the actual calculations, using Doubles. These two classes do not interact
* directly, only through the Controller class. I find this approach a lot simpler. Less connections to keep in mind.
*
* The main caveat of the design:
*   The most glaring issue with this program lies in the way the display is handled. Since the DisplayHandler class
* is not a controller, it has no direct access to the FXML display objects. This means that the Controller class has
* to constantly adjust the actual display values according to the changes made by the actions of the DisplayHandler
* class. This definitely hurts the encapsulation of both classes and means that they are tightly coupled. Also,
* it is assumed that the actual display and the variables of the DisplayHandler class contain the same values
* at any given time. This cannot be enforced easily however, thus this element of the application is prone to
* errors. Please proceed with caution if you wish to modify or expand (or both) it.
*   I considered a different approach, one which uses multiple controllers and FXML files, and relies on
* controller to controller communication. I have decided against using it, however. I find that solution to
* be an "overkill" for such a small application as this. That being said, for bigger programs
*(bigger interface, multiple tabs, etc.) I would definitely opt for that solution.
*
* On the topic of using Doubles for calculation.
*   I understand that Doubles are less precise than Integers, however, for the sake of reduced complexity, I have
* decided to rely on them. I considered using both types in parallel, but such a solution would result in
* an unacceptable amount of duplication. I am sure this can be done better, however this is my first GUI
* application and my first "serious" Java application, so my knowledge in this field is quite limited.
* Of course, this means that this program is not as usable as a "proper" calculator would be. I would
* consider it more to be a "proof of concept" or "prototype".
 */
package com.andrewhun.calculator;

import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Controller {

     // Class variables that indicate the current state of the calculator
     private Operation selectedOperation = Operation.NONE;
     private DisplayState displayState = DisplayState.DEFAULT;

     // The main display, containing the number currently in the focus of the application
     @FXML private Label displayedNumber;

     /* The secondary display, containing the chain of operations and earlier values that belong to the
     current calculation.*/
    @FXML private Label topDisplay;

    // Create getters and setters for the status variables
    public Operation getSelectedOperation() {

        return selectedOperation;
    }

    public void setSelectedOperation(Operation newOperation) {

        selectedOperation = newOperation;
    }

    public DisplayState getDisplayState() {

        return displayState;
    }

    public void setDisplayState(DisplayState newState) {

        displayState = newState;
    }

    // React to the "digit buttons" being pushed
    public void selectActionForDigits(Event event) {

        // Find out which button did the user press
        Button digitButton = (Button) event.getSource();
        String selectedDigit = digitButton.getText();

        // Decide what to show the user depending on the state of the display
        switch (displayState) {

            // Append the selected digit to the currently displayed number
            case CHANGED:

                DisplayHandler.appendDigit(selectedDigit);
                break;

            /*Replace the currently displayed number with the selected digit and
             indicate that the user is now typing in a new number*/
            case DEFAULT:

                DisplayHandler.replaceNumber(selectedDigit);
                displayState = DisplayState.CHANGED;
                break;

            // Show an error in case something goes wrong (redundant due to enum being used)
            default:

                String ERROR_MESSAGE = "An error has occurred";
                DisplayHandler.showErrorMessage(ERROR_MESSAGE);
                resetCalculator();
        }

        // Show changes to the user
        displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
    }

    // React to the backspace button being pushed
    public void selectActionForBackspace() {

        // Only take action if there are digits to delete from the display
        if (displayState == DisplayState.CHANGED) {

            // Decide if the display needs to be reset to default (to avoid completely emptying it)
            if (DisplayHandler.isLastDigit()) {

                // Reset the display and indicate the changes to the program
                DisplayHandler.resetDisplayedNumberString();
                displayState = DisplayState.DEFAULT;
            }

            else {

                // Delete the last digit of the displayed number
                DisplayHandler.deleteDigit();
            }
            // Show changes on the display
            displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
        }
    }

    // Add or remove the minus sign to/from the displayed number
    public void changeSignOfNumber() {

        // Only take action if the user has already started typing in a number
        if (displayState == DisplayState.CHANGED) {

            /*
             * The following conditional is used to address a situation where the number which had the
             * minus sign is deleted, leaving only the minus sign in the display.
             * In that situation, pressing the +/- button deletes the minus sign and leaves the display
             * completely empty. A second press on the same button then causes a
             * StringIndexOutOfBoundsException as the numberIsNegative function is trying to find
             * the first character on the display. This conditional helps in avoiding that.
             */
            if (DisplayHandler.onlyMinusSignIsDisplayed()) {

                DisplayHandler.resetDisplayedNumberString();
                displayState = DisplayState.DEFAULT;
                displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
                return;
            }

            // Find out if the displayed number has a minus sign or not
            Boolean displayedNumIsNegative = DisplayHandler.numberIsNegative();

            // Remove the minus sign
            if (displayedNumIsNegative) {
                DisplayHandler.removeMinusSign();
            }

            // Add a minus sign
            else {

               DisplayHandler.addMinusSign();
            }

            // Show changes on the display
            displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
        }
    }

    // React to the "decimal point button" being pushed
    public void addDecimalPoint() {

        // Append a decimal point to the displayed number if it does not have one yet
        if (DisplayHandler.hasNoDecimalPoint()) {

            DisplayHandler.addDecimalPoint();
            displayedNumber.setText(DisplayHandler.getDisplayedNumberString());

            // Indicate that the display has changed
            displayState = DisplayState.CHANGED;
        }
    }
    /*
     * The "initiateOperation" functions below are used instead of doing a string comparison
     * ("if the button pressed had a + sign as its text value then the user wants to add numbers together.. etc.")
     * So basically they just let the prepareForOperation function know which operation does it need to handle.
     * I find this solution to be less complex than the string comparison, but this is my subjective opinion.
     */

    // React to the "+" button being pressed
    public void initiateAddition() { prepareForOperation(Operation.ADDITION); }

    // React to the "-" button being pressed
    public void initiateSubtraction() { prepareForOperation(Operation.SUBTRACTION); }

    // React to the "*" button being pressed
    public void initiateMultiplication() { prepareForOperation(Operation.MULTIPLICATION); }

    // React to the "/" button being pressed
    public void initiateDivision() { prepareForOperation(Operation.DIVISION); }

    // Prepare the display for the next number
    public void setupDisplayForNextNumber(Operation nextOperation) {

        // Add the next operation and its first number to the top display
        DisplayHandler.addOperationToDisplay(nextOperation);
        topDisplay.setText(DisplayHandler.getTopDisplayString());

        // Make way for the new number on the bottom display
        DisplayHandler.resetDisplayedNumberString();
        displayedNumber.setText(DisplayHandler.getDisplayedNumberString());

        // Let the calculator know a new number is coming up (presumably)
        displayState = DisplayState.DEFAULT;
    }

    /* Change the operation that was selected earlier to match the last choice the user made
    and change the top display to show the correct operation to the user*/

    public void changeOperation(Operation newOperation) {

       DisplayHandler.changeDisplayedOperation(newOperation);
       topDisplay.setText(DisplayHandler.getTopDisplayString());
       selectedOperation = newOperation;
    }

    /* Set up the calculator for executing the selected operation, or initiate the execution of
    an earlier operation if the user is chaining multiple operations in a calculation */
    public void prepareForOperation(Operation newOperation) {

        // The user pressed multiple operator buttons without typing in any numbers
        if (displayState == DisplayState.DEFAULT && DisplayHandler.topDisplayIsNotEmpty()) {

            changeOperation(newOperation);
            return;
        }

        switch (selectedOperation) {

            // Prepare for executing the selected operation
            case NONE:

                // Save the user's input as the first number and indicate which operation is selected
                Double firstNumber = Double.parseDouble(DisplayHandler.getDisplayedNumberString());
                CalculationHandler.setFirstNumber(firstNumber);

                selectedOperation = newOperation;

                setupDisplayForNextNumber(newOperation);
                break;

            // Handle division by zero errors
            case DIVISION:

                // Reset the calculator and show an error message when there is a division by zero error
                if (DisplayHandler.denominatorIsZero()) {

                    resetCalculator();
                    String ERROR_MESSAGE = "Error: Division by zero";
                    DisplayHandler.showErrorMessage(ERROR_MESSAGE);
                    displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
                }

                // Let the division be executed otherwise
                else {

                    executeOperation(newOperation);
                }
                break;

            // If there is an operation already selected execute it
            default:
                executeOperation(newOperation);
        }
    }
    // Execute the selected operation and set up for the next task
    public void executeOperation(Operation newOperation) {

        Double secondNum = Double.parseDouble(DisplayHandler.getDisplayedNumberString());
        CalculationHandler.setSecondNumber(secondNum);

        CalculationHandler.executeOperation(selectedOperation);

        // Set up for the next operation if there is one
        if (newOperation != Operation.NONE) {

            // Store the result of the earlier calculation as the first number of the next one
            CalculationHandler.setFirstNumber(CalculationHandler.getResult());

            // Let the user and the program know which operation is next
            setupDisplayForNextNumber(newOperation);
            selectedOperation = newOperation;
        }
    }

    // Calculate the final result of the calculation when the "=" button is pressed
    public void calculateResult() {

        /* If the user just hit "=" after typing in the first number, keep
         the number on the display and reset the calculator*/
        if (selectedOperation == Operation.NONE) {

            resetCalculator();
            return;
        }

        // Only carry out the (last) operation if the user has entered the second number for it
        if (displayState == DisplayState.CHANGED) {

            // Show a division by zero error when needed
            if (selectedOperation == Operation.DIVISION && DisplayHandler.denominatorIsZero()) {

                final String ERROR_MESSAGE = "Error: Division by zero";
                DisplayHandler.showErrorMessage(ERROR_MESSAGE);
            }

            // Carry out the actual calculation
            else {

                executeOperation(Operation.NONE);
                Double result = CalculationHandler.getResult();
                DisplayHandler.showResult(result.toString());
            }
        }

        // Show the first number if the user hit "=" before entering the second one
        else {

            Double result = CalculationHandler.getFirstNumber();
            DisplayHandler.showResult(result.toString());
        }

        // Show changes on the display and reset the calculator
        displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
        resetCalculator();
    }

    // Reset the calculator to its default state, except for the displayed number
    public void resetCalculator() {

        CalculationHandler.resetNumbers();

        DisplayHandler.resetTopDisplayString();
        topDisplay.setText(DisplayHandler.getTopDisplayString());

        displayState = DisplayState.DEFAULT;
        selectedOperation = Operation.NONE;
    }

    // Erase all information regarding the current calculation when the "C" button is pressed
    public void clearCalculator() {

        resetCalculator();
        DisplayHandler.resetDisplayedNumberString();
        displayedNumber.setText(DisplayHandler.getDisplayedNumberString());
    }
}