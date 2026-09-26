package com.bank.repository;

import com.bank.models.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements  UserRepository {
    private final List<Users> users = new ArrayList<>();

    public InMemoryUserRepository() {
        seedMockData();    
    }

    private void seedMockData() {
        users.add(new Admin(0, "09068845641", 3392, "PAT PILAR"));

        Customer customer1 = new Customer(1, "09239834413", 1423, "DEN DEGUZMAN");
        customer1.addAccount(new SavingsAccount(null, 0));
        customer1.addAccount(new CheckingAccount(null, 0));
        users.add(customer1);

        Customer customer2 = new Customer(2, "09997843277", 9628, "GIL MAURE");
        customer2.addAccount(new SavingsAccount(null, 0));
        customer2.addAccount(new CheckingAccount(null, 0));
        users.add(customer2);
    }
        
    public Users findByMobileAndPin(String mobile, int pin) {
        for (Users u : users) { 
            if (u.getMobileNum().equals(mobile) && u.getPin() == pin) {
                return u;
            } 
        }
        return null;
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        return users;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Users u : users) { 
            if (u instanceof Customer) {
                customers.add((Customer) u);
            } 
        }
        return customers;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admin = new ArrayList<>();
        for (Users u : users) { 
            if (u instanceof Admin) {
                admin.add((Admin) u);
            } 
        }
        return admin;
    }
}

    
