package com.bank.models;
import java.util.ArrayList;
import java.util.List;
import com.bank.views.ConsoleView;

public class Customer extends Users {

    private List<BankAccount> accounts; 
    private final ConsoleView view = new ConsoleView();

    public Customer(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);    
        
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }

    // Getters
    public List<BankAccount> getAccounts() { return accounts; }

    public String getRole() { return "CUSTOMER"; }

    public void openDashboard() { 
        System.out.println("\nOpening Customer Menu for " + getName());  
        view.displayHeader("1. Check Balance  2. Deposit  3. Withdraw  4. Transfer Money   5. History  6. Logout");
    }

    // Find account by specific type
    public BankAccount getAccountByType(String targetType) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountType().equalsIgnoreCase(targetType)) {
                return acc;
            }
        }
        return null;
    }

    // Find account by account number
    public BankAccount getAccountByNumber(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}

