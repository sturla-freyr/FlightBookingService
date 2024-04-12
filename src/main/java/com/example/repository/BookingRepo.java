package com.example.repository;

import java.sql.SQLException;
import com.example.Booking;
import com.example.database.Database;

public class BookingRepo {
    Booking booking;
    public BookingRepo() {
        booking = null;
    }

    /* Here we need to talk to the database */
    public void createBooking(Booking booking) {
        // Assuming the Bookings table includes columns for the booking details,
        // and foreign keys for flightID and userID
        String sql = "INSERT INTO Bookings(serviceName, servicePrice, serviceDescription, flightID, userID, seats) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            // Assuming Database.executeUpdate is equipped to handle PreparedStatement
            // If not, you would typically acquire a connection and create a PreparedStatement manually
            Database.executeUpdate(sql, booking.getServiceName(), booking.getServicePrice(),
                                    booking.getServiceDescription(), booking.getFlight().getFlightID(),
                                    booking.getUser().getUserId(), booking.getSeats());
        } catch (SQLException e) {
            System.out.println("Error creating booking: " + e.getMessage());
        }
    }
    
}
