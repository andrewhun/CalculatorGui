/*
* This file contains the Main class of a GUI calculator application, that is capable of performing
* the four basic operations: addition, subtraction, multiplication and division.
* This class is responsible for starting up the application using calculator.fxml for the visuals.
* This class is NOT responsible for handling user interactions; the Controller class has that responsibility.
 */

package com.andrewhun.calculator;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class Main extends Application {

    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calculator.fxml"));
        Parent root = loader.load();

        controller = loader.getController();

        primaryStage.setTitle("CalculatorGUI");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    // Grant access to the Controller class for testing purposes
    public static Controller getController() {

        return controller;
    }
}
