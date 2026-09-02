package com.bank.models;
import java.util.ArrayList;
import java.util.Scanner;

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

    public double checkAmount(Scanner sc) {
        System.out.println("AMOUNT:");
        while (true) {
            if (sc.hasNextDouble()) {
                double amount = sc.nextDouble();
                return amount; 
            } else {
                System.out.println("\n***INVALID AMOUNT***");
                sc.next();
            }
        }
    }

    // Feature 2: Deposit
    public boolean deposit(double amount) {
        if (amount <= 0 ) {
            return false;
        } else {
            this.balance += amount;
            history.add("Deposit: +P" + amount);
            return true;
        }
    }
    // public void deposit(double amount) {
    //     if (amount > 0) {
    //         balance += amount;
    //         history.add("Deposit: +P" + amount);
    //         balance();
    //     } else {
    //         System.out.println("\n***INVALID AMOUNT***\n");
    //     }        
    // } 
     // Feature 4 (Customer): Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdrew: -P" + amount);
            // balance();
        } else {
            System.out.println("\n***INSUFFICIENT FUNDS.***\n");
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
            balance -= amount;
            history.add("Sent: -P" + amount);
            targetAccount.balance += amount;
            targetAccount.history.add("Received: +P" + amount);
            System.out.println("\n***TRANSFER SUCCESS.***\n");
            System.out.println("***YOUR UPDATED BALANCE: P" + this.getBalance() + "***\n");
        /*if (validAmount(amount, balance)) {
            balance -= amount;
            history.add("Sent: -P" + amount);
            targetAccount.balance += amount;
            targetAccount.history.add("Received: +P" + amount);
            System.out.println("\n***TRANSFER SUCCESS.***\n");
            System.out.println("***YOUR UPDATED BALANCE: P" + this.getBalance() + "***\n");
        } else {
            System.out.println("\n***INSUFFICIENT FUNDS.***\n");
        } */

        // targetAccount.deposit(amount);
        
    }
    // Feature 3 (Customer): Transfer Money
    public void transferMoney(BankAccount targetAccount, double amount, String receiver, String sender) {

        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            this.history.add("Sent: -P" + amount + " To: " + receiver);

            targetAccount.balance += amount;
            targetAccount.history.add("Received: +P" + amount + " From: " + sender);

            System.out.println("\nYOU HAVE SUCCESSFULLY SENT P" + amount + " TO " + receiver);
            // balance();
        
        } else {
           
            System.out.println("\n***INSUFFICIENT AMOUNT OR INVALID AMOUNT***\n");
        }
    }
    

    // Feature 5 (Customer): Transaction History
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