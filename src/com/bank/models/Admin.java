package com.bank.models;

import java.util.List;

public class Admin extends Users{

   public BankAccount account;

    public Admin(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);
    }
    
    //Menu 
    public void menu() {
        System.out.println("\n1. View All Balance   2. View Specific Account   3. Add Fund   4. Deduct Fund   5. Logout");
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