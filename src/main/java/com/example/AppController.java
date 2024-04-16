package com.example;

import com.example.database.Database;
import com.example.repository.BookingRepo;
import com.example.repository.FlightRepo;
import com.example.repository.UserRepo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
  static String bookVbox = "bookVbox";
  private String stadfestNafn;
  private String stadfestKT;
  private String stadfestNF;


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
  @FXML
  TextField fxNF;
  @FXML
  Label fxTime;

  @FXML
  Label fxTimeT;

  @FXML
  VBox fxbookVbox;

  @FXML
  VBox fxstadfestVbox;

  @FXML
  Label fxNafnStadfest;


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


    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
            fxTimeT.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))

    ),
            new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();

    LocalDateTime localDateTime = LocalDateTime.now();
    Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    DateFormat dateFormat = new SimpleDateFormat("dd. MMMM. yyyy");
    String Dags2 = dateFormat.format(date2);
    fxTime.setText(Dags2);


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
    else {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Veldu flug til að halda áfram");
      alert.showAndWait();

    }
    fxstadfestVbox.setVisible(false);

  }

  private void insertData() {
    fxBrottfor.setText(chosenFlight.getDep());
    fxAfangi.setText(chosenFlight.getArr());
    heildarfjoldi = (chosenFlight.getPrice() * fjoldi);
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb, Locale.US);
    formatter.format(" %(,.1f", heildarfjoldi);
    fxVerd.setText(sb + "kr");
    fxSeatText.setText(String.valueOf(fjoldi));
    LocalDateTime localDateTime = chosenFlight.getDepT();
    Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    DateFormat dateFormat = new SimpleDateFormat("dd. MMMM. yyyy");
    String Dags = dateFormat.format(date2);
    fxDags.setText(Dags);

  }

  private void insertVboxData() {

      fxNafnStadfest.setText(fxNafn.getText());

      if (fxNafnStadfest.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Verður að fylla inn Nafn");
      alert.showAndWait();
      fxbookVbox.setVisible(true);
      fxstadfestVbox.setVisible(false);
    }
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

    fxbookVbox.setVisible(false);
    fxstadfestVbox.setVisible(true);
    insertVboxData();


  }
  @FXML
  private void SelectSeats(ActionEvent event) {
    fjoldi = fxSeats.getValue();
    if (chosenFlight != null) {
      heildarfjoldi = (chosenFlight.getPrice() * fjoldi);
    }
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb, Locale.US);
    formatter.format(" %(,.1f", heildarfjoldi);



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
