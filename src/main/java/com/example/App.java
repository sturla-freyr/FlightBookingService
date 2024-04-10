package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.example.database.Database;
public class App extends Application {

    private static void testDatabaseConnection() {
        // Directly call the main method of Database class to test the connection
        Database.main(null); 
    }

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("Hello, JavaFX!");

        StackPane root = new StackPane();
        root.getChildren().add(text);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        testDatabaseConnection();
        launch(args);
    }
}
