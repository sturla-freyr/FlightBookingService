package com.example;

public class FlightController {

    public FlightDatabase db;

    public FlightController(FlightDatabase db) {
        this.db = db;
    }

    /*public MockFlight[] search() {
        return db.emptyQuery();
    }*/

    public Flight[] search(String depLoc, String destLoc){
        return db.getFlightsFromTo(depLoc, destLoc);
    }
/* 
    public MockFlight[] search(String depLoc, Date depTime){
        return db.query(depLoc, depTime);
    }

    public MockFlight[] search(String depLoc, String destLoc, Date depTime){
        return db.query(depLoc, destLoc, depTime);
    }*/

}