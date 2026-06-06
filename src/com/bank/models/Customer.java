package com.bank.models;

public class Customer extends Users {
    // Connects this specific customer to their own bank account
    public BankAccount account;

    public Customer(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);

        // New BankAccount
        this.account = new BankAccount(0);
    }
}

