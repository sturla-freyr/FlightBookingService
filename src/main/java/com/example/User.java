package com.example;

public class User {
    int Id;
    String name;

    public User(int i, String n) {
        Id = i;
        name = n;
    }

    public User(String n) {
        name = n;
    }

    public int getUserId() {
        return this.Id;
    }

    public void setUserId(int id) {
        this.Id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }
}
