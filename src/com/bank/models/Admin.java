package com.bank.models;

import java.util.List;

public class Admin extends Users{

   public BankAccount account;

    public Admin(int id, String mobileNum, int pin, String name) {
        super(id, mobileNum, pin, name);
    }

    // Viewing of All Accounts
    public void viewAllBalances(List<Users> userList) {
        System.out.println("------------------------------------------------");
        System.out.println("\t\tVIEW ALL BALANCES");
        System.out.println("------------------------------------------------");
        System.out.println("FULL NAME\tMOBILE NUMBERS\tBALANCE");
        for (Users u : userList) {
            if (u instanceof Customer) {
                Customer c = (Customer) u;
                System.out.println(c.name + "\t" + c.mobileNum + "\t" + c.account.balance);
            }
        }
        System.out.println("------------------------------------------------");
    }

    // Viewing of Specific Account
    public void viewSingleCustomer(Customer targetCustomer) {
        System.out.println("------------------------------------------------");
        System.out.println("\t\tACCOUNT RESULT");
        System.out.println("------------------------------------------------");
        System.out.println("FULL NAME\tMOBILE NUMBERS\tBALANCE");
        System.out.println(targetCustomer.name + "\t" + targetCustomer.mobileNum + "\t" + targetCustomer.account.balance);
        System.out.println("------------------------------------------------");
    }
}