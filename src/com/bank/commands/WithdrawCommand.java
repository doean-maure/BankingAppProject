package com.bank.commands;

import java.util.List;

import com.bank.models.BankAccount;
import com.bank.models.Customer;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class WithdrawCommand implements Command{
    private final Customer customer;
    private final InputHandler input;
    private final ConsoleView view;
    
    public WithdrawCommand(Customer customer, InputHandler input, ConsoleView view) {
        this.customer = customer;
        this.input = input;
        this.view = view;
    }    

    public void execute() {
        BankAccount account = selectAccount(customer);
        if (account == null) return;
        double amount = input.readDouble("ENTER WITHDRAW AMOUNT");
        if (account.withdraw(amount)) {
            view.displayMessage("Withdraw successful! New balance: P" + account.getBalance());
        } else {
            view.displayErrorMessage("TRANSACTION FAILED");
        }
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
        return "Withdraw Funds";
    }
}
