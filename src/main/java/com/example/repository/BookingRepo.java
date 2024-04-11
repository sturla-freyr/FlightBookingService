package com.example.repository;

import com.example.Booking;
import com.example.BookingDatabase;

public class BookingRepo implements BookingDatabase {
    private final String connectionURL;

    public BookingRepo(String uRL) {
        this.connectionURL = uRL;
    }

    /* Here we need to talk to the database */
    @Override
    public void createBooking(Booking booking) {

    }
}
