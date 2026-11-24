package com.bank.models;
import java.util.ArrayList;

public abstract class BankAccount {
    private String accountNumber;
    protected double balance;
    public ArrayList<String> history; //History log

    public BankAccount(String accountNumner, double initialBalance) {
        this.accountNumber = accountNumner;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
        this.history.add("Account opened with P" + initialBalance + "\n");
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return this.balance; }

    // Polymorphic Method
    public abstract String getAccountType();    

    // Bank logic
    public boolean credit(double amount, String historyLabel) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        history.add(historyLabel +": \t+P" + amount);
        return true;
    }
    
    public boolean debit(double amount, String historyLabel) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        this.balance -= amount;
        history.add(historyLabel +": \t-P" + amount);
        return true;
    }

    // Customer - Deposit
    public boolean deposit(double amount) {
        return credit(amount, "Deposit");
    }

    // Customer - Withdraw
    public boolean withdraw(double amount) {
        return debit(amount, "Withdrew");
    }

    // Customer - Transfer
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount <=0 || targetAccount == null || targetAccount == this) {
            return false;
        }
        if (!this.debit(amount, "Sent")) {
            return false;
        }
       targetAccount.credit(amount, "Received");
       return true; 
    }

    // Customer - Transaction History
    public void history() {
        for (String record : history) {
            System.out.println(record);
        }
    }

    // Admin - Add funds
    public boolean addFunds(double amount) {
        return credit(amount, "Admin Add Funds");
    }
    // Admin - Deduct funds
    public boolean deductFunds(double amount) {
        return debit(amount, "Admin Deduct Funds");
    }

    
    
    

    
}

