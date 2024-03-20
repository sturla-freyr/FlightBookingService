package com.example;

import java.util.ArrayList;
import java.util.List;

public class MockDB implements FlightDatabase{
    public Flight[] MFS = new Flight[3];
    
    public MockDB(){
        
        Flight f1 = new Flight("Reykjavík", "Akureyri");
        Flight f2 = new Flight("Egilsstaðir", "Akureyri");
        Flight f3 = new Flight("Akureyri", "Egilsstaðir");
        this.MFS[0] = f1;
        this.MFS[1] = f2;
        this.MFS[2] = f3;
    }

    public Flight[] getFlightsFromTo(String from, String to){
        List<Flight> matchingFlights = new ArrayList<>();
        if(from != null && to != null){
            for (Flight flight : this.MFS) {
                if (flight.dep.equals(from) && flight.arr.equals(to)) {
                    matchingFlights.add(flight);
                }
            }
        }
        return matchingFlights.toArray(new Flight[0]);
    }

    public Flight[] getFlights(){
        return this.MFS;
    }
}
