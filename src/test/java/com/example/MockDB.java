package com.example;

import java.util.ArrayList;
import java.util.List;

public class MockDB {
    MockFlight[] MFS = new MockFlight[3];
    public MockDB(){
        
        MockFlight f1 = new MockFlight("Reykjavík", "Akureyri");
        MockFlight f2 = new MockFlight("Egilsstaðir", "Akureyri");
        MockFlight f3 = new MockFlight("Akureyri", "Egilsstaðir");
        this.MFS[0] = f1;
        this.MFS[1] = f2;
        this.MFS[2] = f3;
    }

    public MockFlight[] getFlightsFromTo(String from, String to){
        List<MockFlight> matchingFlights = new ArrayList<>();
        if(from != null && to != null){
            for (MockFlight flight : this.MFS) {
                if (flight.dep.equals(from) && flight.arr.equals(to)) {
                    matchingFlights.add(flight);
                }
            }
        }
        return matchingFlights.toArray(new MockFlight[0]);
    }
}
