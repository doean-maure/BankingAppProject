package com.bank.commands;

import com.bank.repository.UserRepository;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class Admin_ViewCustomersCommand implements Command {
    private final InputHandler input;
    private final ConsoleView view;
    private final UserRepository userRepository;

    public Admin_ViewCustomersCommand(InputHandler input, ConsoleView view, UserRepository userRepository) {
        this.input = input;
        this.view = view;
        this.userRepository = userRepository;
    }
    
    public void execute() {
        view.displayHeader("CUSTOMERS ACCOUNTS");
        view.displayMessage("ACCOUNT NAME \tACCOUNT NO. \t BALANCE");
        view.displayAllCustomer(userRepository.getAllCustomers());
    }

    public String getDescription() {
        return "View All Customer Accounts";
    }
}
