package com.example;

import java.util.Date;

public class MockFlight {
    String dep;
    String arr;
    Date depT;
    Date arrT;
    Integer seats;

    public MockFlight(){
        this.dep = "Reykjavík";
        this.arr = "Akureyri";
        this.depT = new Date();
        this.arrT = new Date();
    }

    public MockFlight(String D, String A){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
    }

    public MockFlight(String D, String A, Date DT, Date AT){
        this.dep = D;
        this.arr = A;
        this.depT = DT;
        this.arrT = AT;
    }
}
