package com.example;

import com.example.repository.BookingRepo;

public class BookingController {
    private final BookingRepo BR;

    public BookingController(String URL) {
        BR = new BookingRepo(URL);
    }

    public Booking createBooking(Flight flight, User user, int seats) {
        Booking booking = new Booking(flight, user, seats);
        try {

            BR.createBooking(booking);
        } catch (Exception e) {
            System.err.println("Bókun flugs mistókst");
        }
        return booking;
    }
}
