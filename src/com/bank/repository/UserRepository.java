package com.bank.repository;

import java.util.List;

import com.bank.models.Admin;
import com.bank.models.BankAccount;
import com.bank.models.Customer;
import com.bank.models.Users;

public interface UserRepository {
    Users findByMobileAndPin(String mobile, int pin);
    // Customer findCustomerByMobile(String mobile);
    BankAccount findCustomerByAccount(String acctNumber);
    List<Users> getAllUsers();
    // void saveUser(Users user);
    List<Customer> getAllCustomers();
    List<Admin> getAllAdmins();
    

}
