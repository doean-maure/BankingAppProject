package com.bank.models;

public class Customer extends Users {
    // Connects this specific customer to their own bank account
    public BankAccount account;

    public Customer(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);

        // New BankAccount
        this.account = new BankAccount(0);
    }


    public void menu() {
        System.out.println("\n1. Check Balance  2. Deposit  3. Transfer Money  4. Withdraw  5. History  6. Logout");
    }
}

