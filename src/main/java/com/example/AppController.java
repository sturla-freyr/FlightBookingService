package com.example;

import com.example.database.Database;
import com.example.repository.BookingRepo;
import com.example.repository.FlightRepo;
import com.example.repository.UserRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


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
  private int fjoldi;
  private double heildarfjoldi;
  private String output;
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

  @FXML
  ComboBox <Integer> fxSeats;

  @FXML
  Label fxSeatText;



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
      if (fxSeats.getItems().isEmpty()) {
        List<Integer> seatOptions = Arrays.asList(1, 2, 3, 4);
        fxSeats.getItems().addAll(seatOptions);
      }
    }
    flag = 1;

    fxDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        LocalDate selectedDate = newValue;
        Date selected = java.sql.Date.valueOf(selectedDate);
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
    fxVerd.setText((heildarfjoldi) + "kr");
    fxDags.setText(chosenFlight.getDepT().toString());
    fxSeatText.setText(String.valueOf(fjoldi));
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



    Booking booking = new Booking(chosenFlight, us, fjoldi);
    if(chosenFlight.getSeatsAvailable() >= fjoldi){
      Database.openConnection();
      while(fjoldi > 0){
        chosenFlight.reserveASeat();
        fjoldi--;
      }
      try {
        flights.updateFlight(chosenFlight);

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      Database.closeConnection();
      books.addBooking(booking);

    }
  }
  @FXML
  private void SelectSeats(ActionEvent event) {
    fjoldi = fxSeats.getValue();
    heildarfjoldi =  (chosenFlight.getPrice() * fjoldi);


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
      observableList.clear();
      array = fc.searchFlights(from, to);
      putFlightsToView(array);
      fxFlights.setItems(observableList);
    }
  }

  @FXML
  public void handleMouseClick(MouseEvent arg0) {
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
