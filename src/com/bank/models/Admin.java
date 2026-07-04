package com.bank.models;

import java.util.List;

public class Admin extends Users{

   public BankAccount account;

    public Admin(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);
    }

    // Viewing of All Accounts
    public void viewAll(List<Users> userList) {
        System.out.println("CUSTOMER ACCOUNTS:\n");
        System.out.println("FULL NAME\tMOBILE NUMBERS\tBALANCE");
        
        for (Users user : userList) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                System.out.println(customer.name + "\t" + customer.mobileNum + "\t" + customer.account.balance);
            }
        }
    }

    // Viewing of Specific Account
    public void view(Customer customer) {
        System.out.println("ACCOUNT RESULT:\n");
        System.out.println("FULL NAME\tMOBILE NUMBERS\tBALANCE");
        System.out.println(customer.name + "\t" + customer.mobileNum + "\t" + customer.account.balance);
    }
}