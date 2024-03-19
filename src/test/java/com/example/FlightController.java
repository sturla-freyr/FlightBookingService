package com.example;

public class FlightController {

    MockDB db;

    public FlightController() {
        db = new MockDB();
    }

    /*public MockFlight[] search() {
        return db.emptyQuery();
    }*/

    public MockFlight[] search(String depLoc, String destLoc){
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