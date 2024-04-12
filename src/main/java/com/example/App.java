package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.database.Database;
import com.example.repository.*;
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/bookingApp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Flights");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        Database.initialize();
        launch(args);
    }
}