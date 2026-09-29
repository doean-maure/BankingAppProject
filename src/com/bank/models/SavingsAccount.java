package com.bank.models;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumner, double initialBalance) {
        super(accountNumner, initialBalance);
    }
    
    public String getAccountType() {
        return "SAVINGS ACCOUNT";
    }

    
}