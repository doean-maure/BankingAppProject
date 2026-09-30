package com.bank.views;

import java.util.List;

import com.bank.models.BankAccount;
import com.bank.models.Customer;

public class ConsoleView {

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String error) {
        System.out.println("\n*** " + error + " ***\n");
    }

    public void displayHeader(String header) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(header);
        System.out.println("------------------------------------------------------------");
    }

    public void displayCustomerAccounts(List<Customer> customers) {
        for (Customer customer : customers) {
            for (BankAccount account : customer.getAccounts()) {
                System.out.println(customer.getName() + "\t" + account.getAccountNumber() + "\tP" + account.getBalance());
            }
        }
    }

}
