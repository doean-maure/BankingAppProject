package com.bank.models;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Users {
    private List<BankAccount> accounts; 

    public Customer(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);    
        
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }

    // Getters
    public List<BankAccount> getAccounts() { return accounts; }
}

