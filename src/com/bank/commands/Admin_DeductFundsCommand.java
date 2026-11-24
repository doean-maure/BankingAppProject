package com.bank.commands;

import com.bank.models.BankAccount;
import com.bank.repository.UserRepository;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class Admin_DeductFundsCommand implements Command{
    
private final InputHandler input;
    private final ConsoleView view;
    private final UserRepository userRepository;

    public Admin_DeductFundsCommand(InputHandler input, ConsoleView view, UserRepository userRepository) {
        this.input = input;
        this.view = view;
        this.userRepository = userRepository;
    }
    
    public void execute() {
        double amount = input.readDouble("ENTER AMOUNT");
        String acctNumber = input.readString("ENTER ACCOUNT NUMBER");
        BankAccount targetAccount = userRepository.findCustomerByAccount(acctNumber);
        if (targetAccount == null) return; 
        if (targetAccount.deductFunds(amount)) {
            view.displayHeader("ADMIN ADJUSTMENT");
            view.displayMessage("ACCOUNT NAME \tACCOUNT NO. \t BALANCE");
            view.displayCustomer(userRepository.getAllCustomers(), targetAccount);
        } else {
            view.displayErrorMessage("INSUFFICIENT BALANCE");
        }   
    }

    public String getDescription() {
        return "Deduct Funds";
    }
}

