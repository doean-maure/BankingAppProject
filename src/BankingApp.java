import com.bank.models.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankingApp {

    // Customer targetAccount;
    
    public static void main(String[] args) {
        
        
        
        List<Users> userList = List.of(
            new Admin(0, "09068845641", 3392, "PAT PILAR"),
            new Customer(1, "09239834413", 1423, "DEN DEGUZMAN"),
            new Customer(2, "09997843277", 9628, "GIL MAURE")
        );

         for (Users user : userList) {
            if (user instanceof Customer) {
               Customer customer = (Customer) user;
                if (customer.getId() == 1) {
                customer.addAccount(new SavingsAccount("202644131001", 1000.0));
               } else {
                customer.addAccount(new SavingsAccount("202632271002", 2000.0));
               }
            }
         }

        boolean cont = true;
        boolean loggedIn = false;
        Users authenticateUser = null;
        

        BankingApp service = new BankingApp();
       
        // Login
        while (cont) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nACCOUNT LOGIN\n");
            System.out.println("MOBILE NUMBER:");
            String mobileInput = "09068845641";
            
            try {      
                System.out.println("PIN:");
                int pinInput = 3392;
                
                // Authenticate User
                for (Users u : userList) { 
                    if (u.getMobileNum().equals(mobileInput) && u.getPin() == pinInput) {
                        authenticateUser = u;
                        loggedIn = true;
                        // cont = false;
                        break;
                    } 
                }

                if (!loggedIn) {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) { 
                System.out.println("\n***INCORRECT MOBILE NUMBER OR PIN.***\n");
                sc.nextLine();
            }
            
            // User Logged In
            while (loggedIn) {
                
                // Scanner sc = new Scanner(System.in);
                double amount = 0.0;
                
                if (authenticateUser != null) {
                    
                    // Customer Menu
                    if (authenticateUser instanceof Customer) { 
                        Customer currentCustomer = (Customer) authenticateUser;
                        System.out.println("\n------------------------------------------------------------");
                        
                        int choice = menu(sc, "\n1. Check Balance  2. Deposit  3. Withdraw  4. Transfer Money   5. History  6. Logout", "\n***INVALID INPUT***\n");
                            
                            switch (choice) {
                                case 1:
                                    // currentCustomer.account.balance();
                                    currentCustomer.getAccounts();
                                    for (BankAccount acc : currentCustomer.getAccounts()) {
                                        acc.balance();
                                    }
                                
                                    break;
                                case 2:
                                    amount = amountInput(sc);
                                    // currentCustomer.account.deposit(amount);
                                    break;
                                case 3:
                                    amount = amountInput(sc);
                                    // currentCustomer.account.withdraw(amount);
                                    break;
                                case 4:
                                    Customer targetAccount = service.searchAccount(sc, userList);
                                    try { 
                                        // currentCustomer.account.transferMoney(targetAccount.account, amount, targetAccount.getName(), currentCustomer.getName());
                                    }  catch (NullPointerException e) {
                                        System.out.println("\n***ACCOUNT UNAVAILABLE***\n");
                                    }
                                    break;
                                case 5:
                                    // currentCustomer.account.showHistory(currentCustomer.getName(), currentCustomer.getMobileNum());
                                    break;
                                case 6:
                                    loggedIn = false;
                                    break;
                            }
                        // Admin Menu        
                    } else if (authenticateUser instanceof Admin) {

                        Admin adminUser = (Admin) authenticateUser;
                        System.out.println("------------------------------------------------------------\n");
                    
                        int choice = menu(sc, "\n1. View All Balance   2. View Specific Account   3. Add Fund   4. Deduct Fund   5. Logout\n", "\n***INVALID INPUT***\n");
                            
                        if (choice == 2 || choice == 3 || choice == 4) {

                            Customer targetAccount = service.searchAccount(sc, userList);

                            try {

                                switch (choice) {
                                    case 2:
                                        adminUser.view(targetAccount);
                                        break;
                                    case 3: 
                                        amount = amountInput(sc);
                                        // targetAccount.account.deposit(amount);   
                                        break;
                                    case 4:
                                        amount = amountInput(sc);
                                        // targetAccount.account.withdraw(amount);
                                        break;
                                }
                            } catch (NullPointerException e) {
                                System.out.println("\n***ACCOUNT UNAVAILABLE***\n");
                            }

                        } else {
                            switch (choice) {
                                case 1:
                                    adminUser.viewAll(userList);
                                    break;
                                case 5:
                                    loggedIn = false;    
                                    break;
                            }
                        }
                    }      
                }  
            }
        }
    }
    
   
    
    
    
    public static int menu(Scanner sc, String prompt, String errorMsg) {
        while (true) {
            System.out.println(prompt);
            System.out.println("CHOICE:");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                return choice;
            } else {
                System.out.println(errorMsg);
                sc.nextLine(); 
            } 
            
        }
    }

    public static double amountInput(Scanner sc) {
        while (true) {
            System.out.println("AMOUNT:");
            if (sc.hasNextDouble()) {
                double amount = sc.nextDouble();
                sc.nextLine();
                return amount;
            } else {
                System.out.println("\n***INVALID AMOUNT***\n");
                sc.nextLine(); 
            } 
        }
    }
    
    
    
    public Customer searchAccount(Scanner sc, List<Users> userList) {
        
        Customer targetAccount = null; 
        
        System.out.println("MOBILE NUMBER:");
        String mobile = sc.next();
        
        for (Users user : userList) {
            if (user.getMobileNum().equals(mobile)) {
                if (user instanceof Customer) {
                    targetAccount = (Customer) user;
                } 
            } 
            
        }

        return targetAccount;
    }    
}



