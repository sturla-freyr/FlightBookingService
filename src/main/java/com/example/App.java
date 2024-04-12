package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.database.Database;
public class App extends Application {

    private static void testDatabaseConnection() {
        // Directly call the main method of Database class to test the connection
        Database.main(null); 
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/bookingApp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 504, 709);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        testDatabaseConnection();
        launch(args);
    }
}