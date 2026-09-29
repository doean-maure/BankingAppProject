package com.bank.commands;

import java.util.List;

import com.bank.models.BankAccount;
import com.bank.models.Customer;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class TransactionHistoryCommand implements Command {
    private final Customer customer;
    private final InputHandler input;
    private final ConsoleView view;
    
    public TransactionHistoryCommand(Customer customer, InputHandler input, ConsoleView view) {
        this.customer = customer;
        this.input = input;
        this.view = view;
    }    

     public void execute() {
        BankAccount account = selectAccount(customer);
        // if (account == null) return;
        view.displayHeader("TRANSACTION HISTORY - " + customer.getName());
        view.displayMessage(account.getAccountType() + " ("+ account.getAccountNumber()+")");
        account.history();
        view.displayMessage("Final Balance: \tP" + account.getBalance());
    }

    private BankAccount selectAccount(Customer customer) {
        view.displayMessage("\nSELECT ACCOUNT:");
        List<BankAccount> accounts = customer.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            view.displayMessage((i+1) + ". " + accounts.get(i).getAccountType() + " (" + accounts.get(i).getAccountNumber() + ")");
        }
        System.out.println("------------------------------------------------------------");
        int choice = input.readInt("CHOICE");
        if (choice > 0 && choice <= accounts.size()) {
            return accounts.get(choice - 1);
        } 
        view.displayErrorMessage("INVALID ACCOUNT SELECTION");
        return null;
    }  

    public String getDescription() {
        return "Transaction History";
    }
}
