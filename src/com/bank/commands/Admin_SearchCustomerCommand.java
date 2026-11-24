package com.bank.commands;

import com.bank.models.BankAccount;
import com.bank.repository.UserRepository;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class Admin_SearchCustomerCommand implements Command {
    private final InputHandler input;
    private final ConsoleView view;
    private final UserRepository userRepository;

    public Admin_SearchCustomerCommand(InputHandler input, ConsoleView view, UserRepository userRepository) {
        this.input = input;
        this.view = view;
        this.userRepository = userRepository;
    }    
    
    public void execute() {
        // Display Specific Account
        view.displayHeader("SEARCH CUSTOMER");
        String acctNumber = input.readString("ENTER ACCOUNT NUMBER");
        BankAccount targetAccount = userRepository.findCustomerByAccount(acctNumber);
        view.displayHeader("ACCOUNT RESULT");
        view.displayMessage("ACCOUNT NAME \tACCOUNT NO. \t BALANCE");
        view.displayCustomer(userRepository.getAllCustomers(), targetAccount);

         
        // Deduct Funds
        // double amount = input.readDouble("ENTER TRANSFER AMOUNT");
        // String acctNumber = input.readString("ENTER ACCOUNT NUMBER");
        // // Deposit Logic
        // if(targetAccount.deposit(amount));
        // // Success message
        // view.displayMessage("ADD FUNDS SUCCESSFUL \nCURRENT BALANCE: P" + targetAccount.getBalance());
        
        

    }

    public String getDescription() {
        return "Search Customer Account";
    }
}
