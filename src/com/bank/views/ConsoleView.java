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
        System.out.println("\nCUSTOMER ACCOUNTS:\n");
        System.out.println("FULL NAME\tACCOUNT TYPE\t\tACCOUNT NUMBER\t\tBALANCE");
        for (Customer customer : customers) {
            for (BankAccount acc : customer.getAccounts()) {
                System.out.println(customer.getName() + "\t" + acc.getAccountType() + "\t\t" + acc.getAccountNumber() + "\t\tP" + acc.getBalance());
            }
        }
    }

}
