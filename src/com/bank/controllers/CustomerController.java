package com.bank.controllers;

import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;
import com.bank.commands.*;
import com.bank.models.Customer;
import com.bank.repository.UserRepository;

public class CustomerController {
    private final InputHandler input;
    private final ConsoleView view;
    private final UserRepository userRepository;

    public CustomerController(InputHandler input, ConsoleView view, UserRepository userRepository) {
        this.input = input;
        this.view = view;
        this.userRepository = userRepository;
    }

    public void startSession(Customer customer) {
        MenuController customerMenu = new MenuController(input, view);
        
        // Add options directy to command
        customerMenu.addCommand(1, new CheckBalanceCommand(customer, input, view));
        customerMenu.addCommand(2, new DepositCommand(customer, input, view));
        customerMenu.addCommand(3, new WithdrawCommand(customer, input, view));
        customerMenu.addCommand(4, new TransferCommand(customer, input, view, userRepository));
                        
        // Start menu execution
        customerMenu.runMenu("CUSTOMER DASHBOARD - " + customer.getName());
    }
    
}
