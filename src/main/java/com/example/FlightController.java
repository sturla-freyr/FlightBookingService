package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class FlightController {

    private FlightDatabase db;

    public FlightController(FlightDatabase db) {
        this.db = db;
    }

    public Flight[] search() {
        return getFlights();
    }

    public Flight[] search(String depLoc, String destLoc){
        return getFlightsFromTo(depLoc, destLoc);
    }

    public Flight[] search(String depLoc, Date depTime){
        return getFlightsLocAndTime(depLoc, depTime);
    }

    public Flight[] search(String depLoc, String destLoc, Date depTime){
        return getFlightsDepDestTime(depLoc, destLoc, depTime);
    }

    private Flight[] getFlights(){
        return db.getFlights();
    }
    private Flight[] getFlightsFromTo(String from, String to){
        List<Flight> matchingFlights = new ArrayList<>();
        if(from != null && to != null){
            for (Flight flight : getFlights()) {
                if (flight.dep.equals(from) && flight.arr.equals(to)) {
                    matchingFlights.add(flight);
                }
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }

    private Flight[] getFlightsLocAndTime(String from, Date depTime){
        return getFlights();
    }

    private Flight[] getFlightsDepDestTime(String depLoc, String destLoc, Date depTime){
        return getFlights();
    }

    public void addFlight(Flight f){};
    public void deleteFlight(Flight f){};
    public void changeFlight(){};
}