package com.bank.controllers;

import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;
import com.bank.models.BankAccount;
import com.bank.models.Customer;

public class CustomerController {
    
    private final InputHandler input;
    private final ConsoleView view;

    public CustomerController(InputHandler input, ConsoleView view) {
        this.input = input;
        this.view = view;
    }

    public void showMenu(Customer customer) {
        view.displayMessage("Welcome back, " + customer.getName() + "!");
    
        BankAccount savings = customer.getAccountByType("SAVINGS ACCOUNT");

        if (savings != null) {
            view.displayMessage("SAVINGS BALANCE: P" + savings.getBalance());
        }
    }
}
