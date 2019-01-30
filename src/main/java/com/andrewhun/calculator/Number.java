package com.andrewhun.calculator;
/*
This enum allows the program to keep track of which number the user is currently
interacting with. There cannot be more than two numbers
in use at any given time. If the user is chaining multiple operations, each
should be carried out one at a time, and their results should be stored in as
the first number.
 */
public enum Number {FIRST, SECOND}
