package com.example;

import com.example.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/bookingApp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Velkomin");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args){
        try {
            Database.initialize();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        launch(args);
    }
}
