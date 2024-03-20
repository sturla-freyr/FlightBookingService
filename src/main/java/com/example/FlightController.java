package com.example;

import java.util.ArrayList;
import java.util.List;

public class FlightController {

    public FlightDatabase db;

    public FlightController(FlightDatabase db) {
        this.db = db;
    }

    /*public MockFlight[] search() {
        return db.emptyQuery();
    }*/

    public Flight[] search(String depLoc, String destLoc){
        return getFlightsFromTo(depLoc, destLoc);
    }
/* 
    public MockFlight[] search(String depLoc, Date depTime){
        return db.query(depLoc, depTime);
    }

    public MockFlight[] search(String depLoc, String destLoc, Date depTime){
        return db.query(depLoc, destLoc, depTime);
    }*/

    public Flight[] getFlightsFromTo(String from, String to){
        List<Flight> matchingFlights = new ArrayList<>();
        if(from != null && to != null){
            for (Flight flight : this.db.getFlights()) {
                if (flight.dep.equals(from) && flight.arr.equals(to)) {
                    matchingFlights.add(flight);
                }
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }
}