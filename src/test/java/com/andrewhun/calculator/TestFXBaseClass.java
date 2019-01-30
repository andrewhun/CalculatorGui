/*
* This file contains the TestFXBaseClass, which serves as the base for the two
* test cases using TestFX: MainTestCase and ControllerTestCase. The class contains a lot of
* named constants and a few methods (start, setUp and tearDown)
 */
package com.andrewhun.calculator;

import javafx.stage.Stage;
import org.testfx.api.FxToolkit;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit5.ApplicationTest;

public class TestFXBaseClass extends ApplicationTest {

    // Named constants, used throughout this class
    final String DISPLAYED_NUMBER_ID_TAG = "#displayedNumber";
    final String TOP_DISPLAY_ID_TAG = "#topDisplay";
    final String ZERO_BUTTON_ID_TAG = "#zeroBtn";
    final String ONE_BUTTON_ID_TAG = "#oneBtn";
    final String BACKSPACE_BUTTON_ID_TAG = "#backspace";
    final String CLEAR_BUTTON_ID_TAG = "#clearBtn";
    final String PLUS_BUTTON_ID_TAG = "#plusBtn";
    final String MINUS_BUTTON_ID_TAG = "#minusBtn";
    final String ASTERISK_BUTTON_ID_TAG = "#asterisk";
    final String SLASH_BUTTON_ID_TAG = "#slash";
    final String DECIMAL_POINT_ID_TAG = "#decimalPoint";
    final String EQUALS_BUTTON_ID_TAG = "#equalsBtn";

    final String DEFAULT_DISPLAY_VALUE = "0";
    final String EMPTY = "";

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }
    @BeforeEach
    void setUp() {

        // Clear the calculator before starting the tests, just in case
        clickOn(CLEAR_BUTTON_ID_TAG);
    }

    @AfterEach
    void tearDown() throws Exception {

        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


}
