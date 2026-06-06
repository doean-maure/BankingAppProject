package com.bank.models;
import java.util.ArrayList;

public class BankAccount {
    public double balance;
    public ArrayList<String> history; //History log

    public BankAccount(double balance) {
        this.balance = balance;
        this.history = new ArrayList<>();
        this.history.add("Account opened with P" + balance);
    }

    // Feature 1 (Customer): Check Balance
    public void balance() {
        System.out.println("Your Balance: P" + balance);
        System.out.println("------------------------------------------------");
    }

    // Feature 2 (Customer): Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Cash-in: +P" + amount);
            System.out.println("------------------------------------------------");
            System.out.println("Cash-in successfully!\nYour balance: P" + balance);
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------");
            System.out.println("Invalid amount.");
            System.out.println("------------------------------------------------");
        }        
    }
    
    // Feature 3 (Customer): Transfer Money
    public void transferMoney(BankAccount targeAccount, double amount, String sender, String receiver) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            this.history.add("Sent: -P" + amount + " To: " + receiver);
            targeAccount.balance += amount;
            targeAccount.history.add("Received: +P" + amount + " From: " + sender);
            System.out.println("------------------------------------------------");
            System.out.println("\t\tTRANSFER SUCCESS");
            System.out.println("------------------------------------------------");
            System.out.println("You have successfully sent P" + amount + " to " + receiver);
            System.out.println("Your balance: P" + this.balance);
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------");
            System.out.println("Insufficient funds or invalid amount.");
            System.out.println("------------------------------------------------");
        }
    }

    // Feature 4 (Customer): Withdraw
    // Feature 4 (Admin): Deduct Funds
    public void withdraw(double amount, String role, String name, String mobileNum) {
        if (role.equals("Customer")) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                history.add("Withdrew: -P" + amount);
                System.out.println("------------------------------------------------");
                System.out.println("Updadated Balance: P" + balance);
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------");
                System.out.println("Insufficient funds or invalid amount.");
                System.out.println("------------------------------------------------");
            }
        } else if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Admin Adjustment: -P" + amount);
            System.out.println("------------------------------------------------");
            System.out.println("\t\tP"+amount+" DEDUCTED SUCCESSFULLY!");
            System.out.println("Customer Name: " + name);
            System.out.println("Mobile Number: " + mobileNum);
            System.out.println("Updadated Balance: P" + balance);
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------");
            System.out.println("Invalid amount.");
            System.out.println("------------------------------------------------");
        }
    }

    // Feature 5 (Customer): Transaction History
    public void showHistory(String name, String mobile) {
        System.out.println(name + "\t" + mobile + "\n");
        for (String record : history) {
            System.out.println(record);
        }
        System.out.println("Final Balance: P" + balance);
        System.out.println("------------------------------------------------");
    }
}