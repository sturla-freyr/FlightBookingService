package com.example;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class AppController implements Initializable {
    static ObservableList<Flight> observableList = FXCollections.observableArrayList();

    Flight[] array;

    @FXML
    ComboBox<String> fxCombo;

    @FXML
    ComboBox<String> fxArrivalDest;

    @FXML
    ListView<Flight> fxFlights;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxCombo.setPromptText("auli");
        FlightController fc = new FlightController();
        array = fc.searchFlights();
        putFlightsToView(array);
        fxFlights.setItems(observableList);
        List<String> items = Arrays.asList(
            "Reykjavík",
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

  private void putFlightsToView(Flight[] arr){
    for(int i = 0; i<arr.length; i++){
      observableList.add(arr[i]);
    }
  }

  @FXML
  private void depOnClick(ActionEvent event){
    System.out.println("auli");
  }

}
