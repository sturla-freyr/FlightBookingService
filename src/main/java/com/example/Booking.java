package com.example;

public class Booking {
    Flight flight;
    User user;
    int seats;

    // When a new booking is made, it is initialized with the flight, user and
    // number of seats.
    public Booking(Flight flight, User user, int seats) {
        this.flight = flight;
        this.user = user;
        this.seats = seats;
    }

}
