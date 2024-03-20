package com.example;

public interface FlightDatabase {
    //public Flight[] MFS = new Flight[]{};
    public Flight[] getFlights();
    public Flight[] getFlightsFromTo(String from, String to);
}
