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
        this.history.add("Account opened with P" + balance);
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return this.balance; }

    // Polymorphic Method
    public abstract String getAccountType();    

    // Feature: Deposit Funds
    public boolean deposit(double amount) {
        if (amount > 0 ) {
            this.balance += amount;
            history.add("Deposit: +P" + amount);
            return true;
        } else {
            return false;
        }
    }
    
     // Feature: Withdraw Funds
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdrew: -P" + amount);
            return true;
        } else {
            return false;
        }
    }

    // Feature: Transfer Funds
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Sent: -P" + amount);
            targetAccount.balance += amount;
            targetAccount.history.add("Received: +P" + amount);
            return true;
        } else {
            return false;
        }
    }

    // Feature: Show History
    public void showHistory(String name, String mobile) {
        System.out.println(name + "\t" + mobile + "\n");
        for (String record : history) {
            System.out.println(record);
        }
        System.out.println("Final Balance: P" + balance);
    }


    // ADMIN FEATURES
    public void deposit(double amount, BankAccount targetAccount) {
        if (amount > 0) {
            balance += amount;
            history.add("Admin Adjustment: +P" + amount);
            // balance();
        } else {
            System.out.println("\n***INVALID AMOUNT***\n");
        }        
    }

    public void withdraw(double amount, BankAccount targetAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Admin Adjustment: -P" + amount);
            // balance();
        } else {
            System.out.println("\n***INSUFFICIENT FUNDS.***\n");
        }
    }
}