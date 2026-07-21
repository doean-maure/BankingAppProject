package com.bank.models;

import java.util.InputMismatchException;

public class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 100.0;

    public SavingsAccount(String accountNumner, double initialBalance) {
        super(accountNumner, initialBalance);

        this.balance = MIN_BALANCE;
    }

    public void balance() {
        System.out.println("\nSAVINGS ACCOUNT BALANCE: P" + balance);
    }

    @Override
    public boolean withdraw(double amount) {
        try {
             if (amount > 0 && amount <= balance) {
                return true;
            }
        } catch (InputMismatchException e) {
            // TODO: handle exception
            System.out.println("\n***INSUFFICIENT FUNDS.***\n");
        }
        return false;
    }
}