package com.example;

import java.sql.SQLException;

public interface BookingDatabase {

    public void createBooking(Booking booking) throws SQLException;
}
