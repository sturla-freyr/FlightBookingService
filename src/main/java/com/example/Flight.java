package com.example;

import java.util.Date;

public class Flight {
    String dep;
    String arr;
    Date depT;
    Date arrT;
    Integer seats;

    public Flight(){
        this.dep = "Reykjavík";
        this.arr = "Akureyri";
        this.depT = new Date();
        this.arrT = new Date();
        this .seats = 10;
    }

    public Flight(String D, String A){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this .seats = 10;
    }

    public Flight(String D, String A, Date DT, Date AT){
        this.dep = D;
        this.arr = A;
        this.depT = DT;
        this.arrT = AT;
        this .seats = 10;
    }

    public Flight(String D, String A, Date DT, Date AT, Integer s){
        this.dep = D;
        this.arr = A;
        this.depT = DT;
        this.arrT = AT;
        this .seats = s;
    }

    public Flight(String D, String A, Integer s){
        this.dep = D;
        this.arr = A;
        this.depT = new Date();
        this.arrT = new Date();
        this .seats = s;
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
}
