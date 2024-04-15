package com.example.repository;

import com.example.Flight;
import com.example.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.time.LocalDateTime;

public class FlightRepo{
    
    Flight[] flights;
    

    public FlightRepo(){
        flights = null;
        
    }
    public Flight[] search() {
        return getFlights();
    }

    public Flight[] search(String depLoc, String destLoc) {
        return getFlightsFromTo(depLoc, destLoc);
    }

    public Flight[] search(String depLoc, LocalDateTime depTime) {
        return getFlightsLocAndTime(depLoc, depTime);
    }

    public Flight[] search(String depLoc, String destLoc, LocalDateTime depTime)  {
        return getFlightsDepDestTime(depLoc, destLoc, depTime);
    }

    private Flight[] getFlights() {
        Flight[] fs = null;
        try {
            ResultSet rs = Database.query("SELECT * FROM Flights;");
            fs = resultSetToFlights(rs);           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fs;
    }

    private Flight[] getFlightsFromTo(String from, String to)  {
        List<Flight> matchingFlights = new ArrayList<>();
        if (from != null && to != null) {
            for (Flight flight : getFlights()) {
                if (flight.getDep().equals(from) && flight.getArr().equals(to)) {
                    matchingFlights.add(flight);
                }
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }

    private Flight[] getFlightsLocAndTime(String from, LocalDateTime depLocalDate) {
        List<Flight> matchingFlights = new ArrayList<>();
        
        for (Flight flight : getFlights()) {
            // Convert flight's depT (Date) to LocalDate
            //String depTString = rs.getString("depT");
            //LocalDateTime depT = LocalDateTime.parse(depTString);
            //LocalDateTime flightDate = flight.getDepT().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println(flight.getDep());
            System.out.println(depLocalDate);
            if (flight.getDep().equals(from) && flight.getDepT().equals(depLocalDate)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }
    
    private Flight[] getFlightsDepDestTime(String depLoc, String destLoc, LocalDateTime depTime) {
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : getFlights()) {
            if (flight.getDep().equals(depLoc) && flight.getArr().equals(destLoc) && flight.getDepT().equals(depTime)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }

    public Flight findFlightById(int id) {
        String sql = "SELECT * FROM Flights WHERE id = ?";
        Flight foundFlight = null;

        try {
            ResultSet rs = Database.query(sql, id);

            if (rs.next()) {
                // Extract flight details from the ResultSet
                String dep = rs.getString("dep");
                String arr = rs.getString("arr");
                
                String depTString = rs.getString("depT");
                String arrTString = rs.getString("arrT");
                LocalDateTime depT = LocalDateTime.parse(depTString);
                LocalDateTime arrT = LocalDateTime.parse(arrTString);
                
                int seats = rs.getInt("seats");
                int seatsAvailable = rs.getInt("seatsAvailable");
                double price = rs.getDouble("price");
                int flightId = rs.getInt("id"); // Assuming you have an ID field

                // Create a new Flight object with the extracted data
                foundFlight = new Flight(dep, arr, depT, arrT, seats, seatsAvailable, price, flightId);
            }
        } catch (SQLException e) {
            System.out.println("Error finding flight: " + e.getMessage());
        }

        return foundFlight;
    }

    public static Flight[] resultSetToFlights(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        
        while (rs.next()) {
            String dep = rs.getString("dep");
            String arr = rs.getString("arr");

            String depTString = rs.getString("depT");
            String arrTString = rs.getString("arrT");
            LocalDateTime depT = LocalDateTime.parse(depTString);
            LocalDateTime arrT = LocalDateTime.parse(arrTString);

            Integer seats = rs.getInt("seats");
            Integer seatsAvailable = rs.getInt("seatsAvailable");
            Double price = rs.getDouble("price");
            int flightID = rs.getInt("flightID");  // Retrieve the flightID from the ResultSet

            Flight flight = new Flight(dep, arr, depT, arrT, seats, seatsAvailable, price, flightID);
            flights.add(flight);
        }
        
        // Convert the list to an array and return it
        Flight[] flightArray = new Flight[flights.size()];
        flights.toArray(flightArray);
        return flightArray;
    }

    public void updateFlight(Flight f){
        int id = f.getFlightID();
        int sa = f.getSeatsAvailable() - 1;
        String sql ="""
            UPDATE Flights
            SET seatsAvailable = ?
            WHERE flightID = ?;
        """;

        try {
            Database.executeUpdate(sql, id, sa);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
