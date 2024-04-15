package com.example;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

import com.example.database.Database;
import com.example.repository.BookingRepo;
import com.example.repository.UserRepo;
import com.example.repository.FlightRepo;


public class AppController implements Initializable {
  static ObservableList<Flight> observableList = FXCollections.observableArrayList();

  UserRepo users = new UserRepo();
  BookingRepo books = new BookingRepo();
  FlightRepo flights = new FlightRepo();

  LocalDate fd = LocalDate.now();
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
  Label fxBrottfor;

  @FXML
  Label fxVerd;

  @FXML
  Label fxAfangi;

  @FXML
  Label fxDags;

  @FXML
  TextField fxNafn;

  @FXML
  TextField fxKT;

  @FXML
  Button fxGeraBokun;

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

  int fxSeats = 5;

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
          "New York");

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
        fd = newValue;
      }
    });
  }

  private void putFlightsToView(Flight[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if(arr[i].getDepT().toLocalDate().compareTo(fd) >= 0){
        observableList.add(arr[i]);
      }
    }
  }

  @FXML
  private void bokaScene(ActionEvent event) {
    if (chosenFlight != null) {
      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/bookFlight.fxml"));
      fxmlLoader.setController(this); // Use the same controller
      try {
        Parent root = fxmlLoader.load();
        // Get the current scene and stage
        Scene currentScene = fxBoka.getScene();
        prevScene = currentScene;
        Stage stage = (Stage) currentScene.getWindow();

        // Set the new scene on the stage
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
      } catch (Exception e) {
        System.err.println(e);
      }

      insertData();
    }
  }

  private void insertData() {
    fxBrottfor.setText(chosenFlight.getDep());
    fxAfangi.setText(chosenFlight.getArr());
    fxVerd.setText(chosenFlight.getPrice().toString());
    fxDags.setText(chosenFlight.getDepT().toString());
  }

  @FXML
  private void fxKlaraBokun() throws SQLException {
    int user = users.addUser(fxNafn.getText());
    User us = null;
    try {
      us = users.findUserById(user); 
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    int seatsToBook = fxSeats;//.getInt();
    Booking booking = new Booking(chosenFlight, us, seatsToBook);
    if(chosenFlight.getSeatsAvailable() >= seatsToBook){
      Database.openConnection();
      while(seatsToBook > 0){
        chosenFlight.reserveASeat();
        seatsToBook--;
        System.out.println(chosenFlight.getSeatsAvailable());
      }
      try {
        System.out.println(chosenFlight.getFlightID() + " hæ");
        flights.updateFlight(chosenFlight);
        
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      Database.closeConnection();
      books.addBooking(booking);

    }
  }

  @FXML
  private void depOnClick(ActionEvent event) {
    from = fxCombo.getValue();
    if (to != "") {
      observableList.clear();
      array = fc.searchFlights(from, to);
      putFlightsToView(array);
      fxFlights.setItems(observableList);
    }
  }

  @FXML
  private void endursetjaOnClick(MouseEvent event) {
    observableList.clear();
    array = fc.searchFlights();
    putFlightsToView(array);
    fxFlights.setItems(observableList);
  }

  @FXML
  private void destOnClick(ActionEvent event) {
    to = fxArrivalDest.getValue();
    if (from != "") {
      System.out.println("from: " + from + " to: " + to);
      observableList.clear();
      array = fc.searchFlights(from, to);
      putFlightsToView(array);
      fxFlights.setItems(observableList);
    }
  }

  @FXML
  public void handleMouseClick(MouseEvent arg0) {
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
