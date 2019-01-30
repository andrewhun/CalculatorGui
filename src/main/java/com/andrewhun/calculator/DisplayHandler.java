/*
* This file contains the DisplayHandler class, which is responsible for handling the calculator's
* display element. The Controller class relays relevant information to the user through the
* usage of the member data and functions of this class. The purpose of creating this class
* (along with the CalculationHandler class), was to reduce the overall complexity of the application.
* The DisplayHandler class is equipped with basic getter and setter functions for all of its variables,
* as well as functions that carry out specific tasks on the display (such as displaying new
* operations and changing existing ones). To simplify the process of resetting the calculator
* when the user requests it, reset functions were also created for individual variables.
 */
package com.andrewhun.calculator;

 public class DisplayHandler {

     // Initialize class member data
     private static final String DEFAULT_DISPLAYED_NUMBER = "0";
     private static final String EMPTY = "";

     private static String topDisplayString = EMPTY;
     private static String displayedNumberString = DEFAULT_DISPLAYED_NUMBER;

     // Named constants, for internal use only
     private static final String PLUS_SIGN  = " + ";
     private static final String MINUS_SIGN = " - ";
     private static final String ASTERISK = " * ";
     private static final String SLASH = " / ";

     private static final String MINUS_SIGN_NO_SPACES = "-";

     private static final String DECIMAL_POINT = ".";

     private static final String ZERO_WITH_DECIMAL_POINT = DEFAULT_DISPLAYED_NUMBER + DECIMAL_POINT;

     // Create setters, getters and reset functions for the class member data
     public static String getTopDisplayString() {

         return topDisplayString;
     }

     public static void setTopDisplayString(String newOperation) {

         topDisplayString= newOperation;
     }

     public static void resetTopDisplayString() {

         topDisplayString = EMPTY;
     }

     public static String getDisplayedNumberString() {

         return displayedNumberString;
     }

     public static void setDisplayedNumberString(String newNumber) {

         displayedNumberString= newNumber;
     }

     public static void resetDisplayedNumberString () {

         displayedNumberString = DEFAULT_DISPLAYED_NUMBER;
     }

     // Append the selected digit to the displayed number
     public static void appendDigit(String selectedDigit) {

         displayedNumberString += selectedDigit;
     }

     // Replace the currently displayed number with the selected digit
     public static void replaceNumber(String selectedDigit) {

         displayedNumberString = selectedDigit;
     }

     public static void showErrorMessage(String errorMessage) {

         displayedNumberString = errorMessage;
     }

     // Decide if the currently displayed number has only one digit
     public static Boolean isLastDigit() {

         return (displayedNumberString.length() == 1 || displayedNumberString.equals(ZERO_WITH_DECIMAL_POINT));
     }

     // Delete the last digit of the currently displayed number
     public static void deleteDigit() {

         displayedNumberString = displayedNumberString.substring(0, displayedNumberString.length() - 1);
     }

     // Find out if the user has deleted the number which had a minus sign assigned
     public static Boolean onlyMinusSignIsDisplayed(){

         return (displayedNumberString.equals(MINUS_SIGN_NO_SPACES));
     }

     // Find out if the displayed number is positive or negative
     public static Boolean numberIsNegative() {

         return(displayedNumberString.startsWith(MINUS_SIGN_NO_SPACES));
     }

     // Add a minus sign to the displayed number
     public static void addMinusSign() {

         displayedNumberString = MINUS_SIGN_NO_SPACES + displayedNumberString;
     }

     // Remove the minus sign from the displayed number
     public static void removeMinusSign() {

         displayedNumberString = displayedNumberString.substring(1);
     }

     // Find out if the displayed number is has a decimal point
     public static Boolean hasNoDecimalPoint() {

         return(!displayedNumberString.contains(DECIMAL_POINT));
     }

     // Add a decimal point to the displayed number
     public static void addDecimalPoint() {

         displayedNumberString += DECIMAL_POINT;
     }

     // Add the selected number and operation to the top display
     public static void addOperationToDisplay(Operation newOperation) {

         // Substitute zero for zero with decimal point
         if (displayedNumberString.equals(ZERO_WITH_DECIMAL_POINT)) {

             resetDisplayedNumberString();
         }
         String displayWithNumber = topDisplayString + displayedNumberString;

         switch (newOperation) {

             case ADDITION:

                 topDisplayString = displayWithNumber + PLUS_SIGN;
                 break;

             case SUBTRACTION:

                 topDisplayString = displayWithNumber + MINUS_SIGN;
                 break;

             case MULTIPLICATION:

                 topDisplayString = displayWithNumber + ASTERISK;
                 break;

             case DIVISION:

                 topDisplayString = displayWithNumber + SLASH;
                 break;
         }
     }

     // Change the operation shown on the top display
     public static void changeDisplayedOperation(Operation newOperation) {

         String displayWithoutOperation = topDisplayString.substring(0, topDisplayString.length() - 3);

         switch (newOperation) {

             case ADDITION:

                 topDisplayString = displayWithoutOperation + PLUS_SIGN;
                 break;

             case SUBTRACTION:

                 topDisplayString = displayWithoutOperation + MINUS_SIGN;
                 break;

             case MULTIPLICATION:

                 topDisplayString = displayWithoutOperation + ASTERISK;
                 break;

             case DIVISION:

                 topDisplayString = displayWithoutOperation + SLASH;
         }
     }

     // Find out if the top display is empty
     public static Boolean topDisplayIsNotEmpty() {

         return (!topDisplayString.equals(EMPTY));
     }

     // Find out if the user is trying to divide numbers by zero
     public static Boolean denominatorIsZero() {

         return(displayedNumberString.equals(DEFAULT_DISPLAYED_NUMBER) ||
                 displayedNumberString.equals(ZERO_WITH_DECIMAL_POINT));
     }

     // Find out if there was a division by zero error during the calculation
     public static Boolean errorFreeCalculation() {

         final String ERROR_MESSAGE = "Error: Division by zero";
         return (!displayedNumberString.equals(ERROR_MESSAGE));
     }

     // Show the result of the calculation
     public static void showResult(String result) {

         String EXCESS_DECIMAL_VALUE = ".0";
         // Remove decimal values if the result is a whole number
         if (result.endsWith(EXCESS_DECIMAL_VALUE)) {

             displayedNumberString = result.substring(0, result.length() -2);
         }

         // Show result as is otherwise
         else {

             displayedNumberString = result;
         }
     }


}
