package com.andrewhun.calculator;
/*
This enum contains the two possible states the calculator can have:
it is either calculating (i. e. the user has started typing in digits),
or it is in its default state (waiting for user input while displaying
either 0 or the result of the previous calculation)
 */
public enum CalculatorState {DEFAULT, CALCULATING}
