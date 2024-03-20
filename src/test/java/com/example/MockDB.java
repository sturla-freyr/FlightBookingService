package com.example;

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

    public Flight[] getFlights(){
        return this.MFS;
    }

}
