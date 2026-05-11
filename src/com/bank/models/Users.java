package com.bank.models;

public class Users {
    int id;
    String mobileNum;
    int pin;
    String name;
    String address;
    double balance;
    String role;

    //Constructor
    public Users(int id, String mobileNum, int pin, String name, String address, double balance, String role) {
        this.id = id;
        this.mobileNum = mobileNum;
        this.pin = pin;
        this.name = name;
        this.address = address;
        this.balance = balance;
        this.role = role;
    }

    //Getters
    public int getId() { return id; }
    public String getMobileNum() { return mobileNum; }
    public int getPin() { return pin; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getBalance() { return balance; }
    public String getRole() { return role; }


    //Setters
    public void setId(int id) { this.id = id; }
    public void setMobileNum(String mobileNum) { this.mobileNum = mobileNum; }
    public void setPin(int pin) { this.pin = pin; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setRole(String role) { this.role = role; }
}
