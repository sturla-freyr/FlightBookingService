package com.example;

import com.example.repository.BookingRepo;

public class BookingController {
    private final BookingRepo BR;

    public BookingController() {
        BR = new BookingRepo();
    }

    public Booking createBooking(Flight flight, User user, int seats) {
        Booking booking = new Booking(flight, user, seats);
        try {

            BR.addBooking(booking);
        } catch (Exception e) {
            System.err.println("Bókun flugs mistókst");
        }
        return booking;
    }
}
