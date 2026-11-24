package com.bank.commands;

import com.bank.repository.UserRepository;
import com.bank.views.ConsoleView;

public class Admin_ViewCustomersCommand implements Command {
    private final ConsoleView view;
    private final UserRepository userRepository;

    public Admin_ViewCustomersCommand(ConsoleView view, UserRepository userRepository) {
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
