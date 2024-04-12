package com.example.repository;

import java.sql.SQLException;
import java.sql.ResultSet;
import com.example.Booking;
import com.example.database.Database;
import com.example.User;
import com.example.Flight;

public class BookingRepo {
    Booking booking;
    public BookingRepo() {
        booking = null;
    }

    public void addBooking(Booking booking) {
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
    
    public Booking findBookingById(int id) {
        String sql = "SELECT * FROM Bookings WHERE id = ?";
        Booking foundBooking = null;
    
        try {
            ResultSet rs = Database.query(sql, id);
    
            if (rs.next()) {
                FlightRepo fr = new FlightRepo();
                UserRepo ur = new UserRepo();
                // Extract booking details
                String serviceName = rs.getString("serviceName");
                Double servicePrice = rs.getDouble("servicePrice");
                String serviceDescription = rs.getString("serviceDescription");
                int flightId = rs.getInt("flightID");
                int userId = rs.getInt("userID");
                int seats = rs.getInt("seats");
                
                // Fetch the associated Flight and User based on their IDs
                Flight flight = fr.findFlightById(flightId);
                User user = ur.findUserById(userId); 
    
                foundBooking = new Booking(serviceName, servicePrice, serviceDescription, flight, user, seats);
            }
        } catch (SQLException e) {
            System.out.println("Error finding booking: " + e.getMessage());
        }
    
        return foundBooking;
    }
    

    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM Bookings WHERE id = ?";
        boolean isSuccess = false;
        
        try {
            Database.executeUpdate(sql, id);
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
        
        return isSuccess;
    }
    
    
}
