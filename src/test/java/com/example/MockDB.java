package com.example;

public class MockDB{
    public Flight[] MFS = new Flight[3];
    
    public MockDB(){
        
        this.MFS[0] = new Flight("Reykjavík", "Akureyri");
        this.MFS[1] = new Flight("Egilsstaðir", "Akureyri");
        this.MFS[2] = new Flight("Akureyri", "Egilsstaðir");
        
    }

    public Flight[] getFlights(){
        return this.MFS;
    }

}
