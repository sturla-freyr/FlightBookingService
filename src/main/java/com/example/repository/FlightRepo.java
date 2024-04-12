package com.example.repository;

import com.example.Flight;
import com.example.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;

public class FlightRepo {
    
    Flight[] flights;
    

    public FlightRepo(){
        flights = null;
        try {
            System.out.println(Database.getConnection()); 
            System.out.println("yay");   
        } catch (SQLException e) {
            // TODO: handle exception
        }
        
    }
    public Flight[] search() {
        return getFlights();
    }

    public Flight[] search(String depLoc, String destLoc) {
        return getFlightsFromTo(depLoc, destLoc);
    }

    public Flight[] search(String depLoc, Date depTime) {
        return getFlightsLocAndTime(depLoc, depTime);
    }

    public Flight[] search(String depLoc, String destLoc, Date depTime) {
        return getFlightsDepDestTime(depLoc, destLoc, depTime);
    }

    private Flight[] getFlights() {
        try {
            ResultSet rs = Database.query("SELECT * FROM Users;");            
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return null;
    }

    private Flight[] getFlightsFromTo(String from, String to) {
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

    private Flight[] getFlightsLocAndTime(String from, Date depTime) {
        return getFlights();
    }

    private Flight[] getFlightsDepDestTime(String depLoc, String destLoc, Date depTime) {
        return getFlights();
    }
}
