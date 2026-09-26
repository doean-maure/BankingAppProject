package com.bank.models;

import java.util.List;
import com.bank.views.ConsoleView;

public class Admin extends Users{

    public BankAccount accounts;
    private final ConsoleView view = new ConsoleView();

    public Admin(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);
    }

    // Role
    public String getRole() { return "ADMIN"; }

    public void openDashboard() {
        System.out.println("\nOpening Admin Menu for " + getName());  
        view.displayHeader("1. View All Balance   2. View Specific Account   3. Add Fund   4. Deduct Fund   5. Logout");

    }

    // Viewing of All Accounts
    public void viewAll(List<Users> userList) {
        System.out.println("\nCUSTOMER ACCOUNTS:\n");
        System.out.println("FULL NAME\tACCOUNT TYPE\t\tACCOUNT NUMBERS\t\tBALANCE");
        for (Users user : userList) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                for (BankAccount acc : customer.getAccounts()) { 
                    System.out.println(customer.name + "\t" + acc.getAccountType() + acc.getAccountNumber() + "\t\t" + acc.balance);
                }
            }
        }
    }

    // Viewing of Specific Account
    public void viewAcc(List<Users> userList, BankAccount targetAccount) {

        System.out.println("\nACCOUNT RESULT:\n");
        System.out.println("FULL NAME\tACCOUNT TYPE\t\tACCOUNT NUMBERS\t\tBALANCE");

        for (Users users : userList) {
            if (users instanceof Customer) {
                Customer customer = ((Customer)users);
                for (BankAccount account : customer.getAccounts()) {
                    if (account.getAccountNumber().equals(targetAccount.getAccountNumber())) {
                        System.out.println(customer.getName() + "\t" + account.getAccountType() + "\t\t" + account.getAccountNumber() + "\t\t" + account.getBalance());
                    }     
                }
            } 
        }
        
    }
}