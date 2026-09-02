package com.bank.models;

public class CheckingAccount extends BankAccount {
    private static final double MIN_BALANCE = 100.0;

    public CheckingAccount(String accountNumner, double initialBalance) {
        super(accountNumner, initialBalance);

        this.balance = MIN_BALANCE;
    }

    public String getAccountType() {
        return "CHECKING ACCOUNT";
    }

    
}
