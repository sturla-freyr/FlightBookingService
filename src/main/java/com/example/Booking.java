package com.example;

public class Booking implements Service {
    String serviceName;
    Double servicePrice;
    String serviceDescription;
    Flight flight;
    User user;
    int seats;

    public String getServiceName(){
        return this.serviceName;
    };
    public void setServiceName(String sn){
        this.serviceName = sn;
    };
    public Double getServicePrice(){
        return this.servicePrice;
    };
    public void setServicePrice(Double sp){
        this.servicePrice = sp;
    };
    public String getServiceDescription(){
        return this.serviceDescription;
    };
    public void setServiceDescription(String sd){
        this.serviceDescription = sd;
    };

    // When a new booking is made, it is initialized with the flight, user and
    // number of seats.
    public Booking(Flight flight, User user, int seats) {
        this.flight = flight;
        this.user = user;
        this.seats = seats;
    }

}
