package com.bank.models;
import java.util.ArrayList;

public abstract class BankAccount {
    private String accountNumber;
    protected double balance;
    public ArrayList<String> history; //History log

    public BankAccount( String accountNumner, double initialBalance) {
        this.accountNumber = accountNumner;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
        this.history.add("Account opened with P" + balance);
    }

    // Feature 1 (Customer): Check Balance
    public void balance() {
        System.out.println("\nBALANCE: P" + balance);
    }

    // Feature 2 (Customer): Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Deposit: +P" + amount);
            System.out.println("\nUPDATED BALANCE: P" + balance);
        } else {
            System.out.println("\n***INVALID AMOUNT***\n");
        }        
    }
     // Feature 4 (Customer): Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdrew: -P" + amount);
            System.out.println("\nUPDATED BALANCE: P" + balance);
        } else {
            System.out.println("\n***INSUFFICIENT FUNDS.***\n");
        }
        
    }
    // Feature 3 (Customer): Transfer Money
    public void transferMoney(BankAccount targetAccount, double amount, String receiver, String sender) {

        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            this.history.add("Sent: -P" + amount + " To: " + receiver);

            targetAccount.balance += amount;
            targetAccount.history.add("Received: +P" + amount + " From: " + sender);

            System.out.println("\nYOU HAVE SUCCESSFULLY SENT P" + amount + " TO " + receiver);
            System.out.println("YOUR UPDATED BALANCE: P" + this.balance);
        
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

    
}