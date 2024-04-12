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
    public Flight getFlight(){
        return this.flight;
    }
    public void setFlight(Flight f){
        this.flight = f;
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(User u){
        this.user = u;
    }
    public int getSeats(){
        return this.seats;
    }
    public void setSeats(int s){
        this.seats = s;
    }
    // When a new booking is made, it is initialized with the flight, user and
    // number of seats to reserve.
    public Booking(Flight flight, User user, int seats) {
        this.flight = flight;
        this.user = user;
        this.seats = seats;
        this.serviceName = flight.getDep() + " to " + flight.getArr() + " Flight";
        this.servicePrice = flight.getPrice() * seats;
        this.serviceDescription = "Booking for " + user.getName() + ": " + seats + " seat(s) from " +
                                  flight.getDep() + " to " + flight.getArr() + " on " + flight.getDepT().toString();
    }

    public Booking(String serviceName, Double servicePrice, String serviceDescription, Flight flight, User user, int seats) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.serviceDescription = serviceDescription;
        this.flight = flight;
        this.user = user;
        this.seats = seats;
    }
    

}
