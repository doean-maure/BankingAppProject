package com.bank.models;

public class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 100.0;

    public SavingsAccount(String accountNumner, double initialBalance) {
        super(accountNumner, initialBalance);
    }

    
}