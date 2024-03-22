package com.example;

import java.util.Date;

public class Flight {
    String dep;
    String arr;
    Date depT;
    Date arrT;
    Integer seats;
    Double price;

    public Flight(){
        this.dep = "Reykjavík";
        this.arr = "Akureyri";
        this.depT = new Date();
        this.arrT = new Date();
        this.seats = 10;
        this.price = 15000.0;
    }

    public Flight(String D, String A){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this.seats = 10;
        this.price = 15000.0;
    }

    public Flight(String D, String A, Integer s, Double p){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this.seats = s;
        this.price = p;
    }

    public Flight(String D, String A, Double p){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this.seats = 10;
        this.price = p;
    }

    public Flight(String D, String A, Date DT, Date AT){
        this.dep = D;
        this.arr = A;
        this.depT = DT;
        this.arrT = AT;
        this.seats = 10;
        this.price = 15000.0;
    }

    public Flight(String D, String A, Date DT, Date AT, Integer s){
        this.dep = D;
        this.arr = A;
        this.depT = DT;
        this.arrT = AT;
        this.seats = s;
        this.price = 15000.0;
    }

    public Flight(String D, String A, Integer s){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this.seats = s;
        this.price = 15000.0;
    }

    // Getter for departure location
    public String getDep() {
        return dep;
    }

    // Setter for departure location
    public void setDep(String dep) {
        this.dep = dep;
    }

    // Getter for arrival location
    public String getArr() {
        return arr;
    }

    // Setter for arrival location
    public void setArr(String arr) {
        this.arr = arr;
    }

    // Getter for departure time
    public Date getDepT() {
        return depT;
    }

    // Setter for departure time
    public void setDepT(Date depT) {
        this.depT = depT;
    }

    // Getter for arrival time
    public Date getArrT() {
        return arrT;
    }

    // Setter for arrival time
    public void setArrT(Date arrT) {
        this.arrT = arrT;
    }

    // Getter for seats
    public Integer getSeats() {
        return seats;
    }

    // Setter for seats
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }
}
