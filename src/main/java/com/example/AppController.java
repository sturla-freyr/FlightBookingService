package com.example;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AppController implements Initializable {
    static ObservableList<Flight> observableList = FXCollections.observableArrayList();

    int flag = 0;
    Flight[] array;
    static Flight chosenFlight = null;
    private String from = "";
    private String to = "";
    private Scene prevScene = null;
    private FlightController fc;
    @FXML
    ComboBox<String> fxCombo;

    @FXML
    Button fxHaetta;

    @FXML
    ComboBox<String> fxArrivalDest;

    @FXML
    ListView<Flight> fxFlights;

    @FXML
    Button fxEndursetja;

    @FXML
    Button fxBoka;

    @FXML
    DatePicker fxDatePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      if (flag == 0) {
        fc = new FlightController();
        array = fc.searchFlights();
        putFlightsToView(array);
        fxFlights.setItems(observableList);
        List<String> items = Arrays.asList(
            "Reykjavik",
            "Akureyri",
            "London",
            "New York"
        );

        // Set the items to the ComboBox
        fxCombo.getItems().addAll(items);
        fxArrivalDest.getItems().addAll(items);

        // Optionally, you can set the prompt text for the ComboBox
        fxCombo.setPromptText("Veldu brottfararstað");
        fxArrivalDest.setPromptText("Veldu áfangastað");
      } 
      flag = 1;

      fxDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            LocalDate selectedDate = newValue;
            Date selected = java.sql.Date.valueOf(selectedDate);
            System.out.println("Selected Date: " + selected);
            // You can perform any action with the selected date here
        }
    });
  }

  private void putFlightsToView(Flight[] arr){
    for(int i = 0; i<arr.length; i++){
      observableList.add(arr[i]);
    }
  }

  @FXML 
  private void bokaScene(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/bookFlight.fxml"));
      fxmlLoader.setController(this); // Use the same controller
      Parent root = fxmlLoader.load();
      // Get the current scene and stage
      Scene currentScene = fxBoka.getScene();
      prevScene = currentScene;
      Stage stage = (Stage) currentScene.getWindow();
      
      // Set the new scene on the stage
      Scene newScene = new Scene(root);
      stage.setScene(newScene);
  }
  

  @FXML
  private void depOnClick(ActionEvent event){
      from = fxCombo.getValue();
      if(to != ""){
        observableList.clear();
        array = fc.searchFlights(from, to);
        putFlightsToView(array);
        fxFlights.setItems(observableList);
      }
  }

  @FXML
  private void endursetjaOnClick(MouseEvent event){
    observableList.clear();
    array = fc.searchFlights();
    putFlightsToView(array);
    fxFlights.setItems(observableList);
  }

  @FXML
  private void destOnClick(ActionEvent event){
    to = fxArrivalDest.getValue();
    if(from != ""){
      System.out.println("from: " + from + " to: " + to);
      observableList.clear();
      array = fc.searchFlights(from, to);
      putFlightsToView(array);
      fxFlights.setItems(observableList);
    }
  }

  @FXML public void handleMouseClick(MouseEvent arg0) {
    System.out.println("clicked on " + fxFlights.getSelectionModel().getSelectedItem());
    chosenFlight = fxFlights.getSelectionModel().getSelectedItem();
}

@FXML 
private void backToPrevScene(ActionEvent event) {
    if (prevScene != null) {
        Stage stage = (Stage) fxHaetta.getScene().getWindow();
        stage.setScene(prevScene);
    } else {
        // Handle the case where there is no previous scene to go back to
        System.out.println("No previous scene available");
    }
}

}
