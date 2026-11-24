package com.bank.controllers;

import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;
import com.bank.commands.*;
import com.bank.models.Admin;
import com.bank.repository.UserRepository;

public class AdminController {
    private final InputHandler input;
    private final ConsoleView view;
    private final UserRepository userRepository;

    public AdminController(InputHandler input, ConsoleView view, UserRepository userRepository) {
        this.input = input;
        this.view = view;
        this.userRepository = userRepository;
    }

    public void startSession(Admin admin) {
        MenuController adminMenu = new MenuController(input, view);
        
        // Add options directy to command
        adminMenu.addCommand(1, new Admin_ViewCustomersCommand(view, userRepository));
        adminMenu.addCommand(2, new Admin_SearchCustomerCommand(input, view, userRepository));
        adminMenu.addCommand(3, new Admin_AddFundsCommand(input, view, userRepository));
        adminMenu.addCommand(4, new Admin_DeductFundsCommand(input, view, userRepository));
        
        // Start menu execution
        adminMenu.runMenu("CUSTOMER DASHBOARD - " + admin.getName());
    }
    
}
