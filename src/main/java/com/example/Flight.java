package com.example;

import java.time.LocalDateTime;

public class Flight {
    String dep;
    String arr;
    LocalDateTime depT;
    LocalDateTime arrT;
    Integer seats;
    Integer seatsAvailable;
    Double price;
    int Id;

    public Flight() {
        dep = "Reykjavík";
        arr = "Akureyri";
        depT = LocalDateTime.now();
        arrT = LocalDateTime.now();
        seats = 10;
        seatsAvailable = seats;
        price = 15000.0;
    }

    public Flight(String D, String A) {
        dep = D;
        arr = A;
        depT = LocalDateTime.now();
        arrT = LocalDateTime.now();
        seats = 10;
        seatsAvailable = seats;
        price = 15000.0;
    }

    public Flight(String D, String A, Integer s, Double p) {
        dep = D;
        arr = A;
        depT = LocalDateTime.now();
        arrT = LocalDateTime.now();
        seats = s;
        seatsAvailable = seats;
        price = p;
    }

    public Flight(String D, String A, Double p) {
        dep = D;
        arr = A;
        depT = LocalDateTime.now();
        arrT = LocalDateTime.now();
        seats = 10;
        seatsAvailable = seats;
        price = p;
    }

    public Flight(String D, String A, LocalDateTime DT, LocalDateTime AT) {
        dep = D;
        arr = A;
        depT = DT;
        arrT = AT;
        seats = 10;
        seatsAvailable = seats;
        price = 15000.0;
    }

    public Flight(String D, String A, LocalDateTime DT, LocalDateTime AT, Integer s) {
        dep = D;
        arr = A;
        depT = DT;
        arrT = AT;
        seats = s;
        seatsAvailable = seats;
        price = 15000.0;
    }

    public Flight(String D, String A, Integer s) {
        dep = D;
        arr = A;
        depT = LocalDateTime.now();
        arrT = LocalDateTime.now();
        seats = s;
        seatsAvailable = seats;
        price = 15000.0;
    }

    public Flight(String dep, String arr, LocalDateTime depT, LocalDateTime arrT, Integer seats, Integer seatsAvailable, Double price) {
        this.dep = dep;
        this.arr = arr;
        this.depT = depT;
        this.arrT = arrT;
        this.seats = seats;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
    }

    public Flight(String dep, String arr, LocalDateTime depT, LocalDateTime arrT, Integer seats, Integer seatsAvailable, Double price,
            int id) {
        this.dep = dep;
        this.arr = arr;
        this.depT = depT;
        this.arrT = arrT;
        this.seats = seats;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
        this.Id = id;
    }

    // Getter for departure location
    public String getDep() {
        return dep;
    }

    // Setter for departure location
    public void setDep(String dep) {
        this.dep = dep;
    }

    // Getter for arrival location
    public String getArr() {
        return arr;
    }

    // Setter for arrival location
    public void setArr(String arr) {
        this.arr = arr;
    }

    // Getter for departure time
    public LocalDateTime getDepT() {
        return depT;
    }

    // Setter for departure time
    public void setDepT(LocalDateTime depT) {
        this.depT = depT;
    }

    // Getter for arrival time
    public LocalDateTime getArrT() {
        return arrT;
    }

    // Setter for arrival time
    public void setArrT(LocalDateTime arrT) {
        this.arrT = arrT;
    }

    // Getter for seats
    public Integer getSeats() {
        return seats;
    }

    // Setter for seats
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeatsAvailable() {
        return this.seatsAvailable;
    }

    public void setSeatsAvailable(Integer s) {
        this.seatsAvailable = s;
    }

    public void reserveASeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public int getFlightID() {
        return this.Id;
    }

    public void setFlightID(int id) {
        this.Id = id;
    }

    @Override
    public String toString() {
        // Customize how the Flight object is displayed in the ListView
        return "Frá: " + dep +
                ", Til: " + arr +
                ", Dagsetning: " + depT + ", Sætafjöldi: " + seatsAvailable;
    }
}
