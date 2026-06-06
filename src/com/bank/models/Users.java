package com.bank.models;

public class Users {
    int id;
    String mobileNum;
    int pin;
    String name;

    //Constructor
    public Users(int id, String mobileNum, int pin, String name) {
        this.id = id;
        this.mobileNum = mobileNum;
        this.pin = pin;
        this.name = name;
    }

    //Getters
    public int getId() { return id; }
    public String getMobileNum() { return mobileNum; }
    public int getPin() { return pin; }
    public String getName() { return name; }
}
