import com.bank.models.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankingApp {
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
                customer.addAccount(new SavingsAccount("1202644131", 0));
                customer.addAccount(new CheckingAccount("220264413", 0));
               } else {
                customer.addAccount(new SavingsAccount("1202632271", 0));
                customer.addAccount(new CheckingAccount("2202632271", 0));
               }
            }
         }

        boolean cont = true;
        boolean loggedIn = false;
        Users authenticateUser = null;
       
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
                
                    if (authenticateUser != null) {

                        BankingApp query = new BankingApp();

                        // Customer Menu
                        if (authenticateUser instanceof Customer) { 
                            Customer currentCustomer = (Customer) authenticateUser;
                            System.out.println("\n------------------------------------------------------------");

                            int choice = menu(sc, "\n1. Check Balance  2. Deposit  3. Withdraw  4. Transfer Money   5. History  6. Logout", "\n***INVALID INPUT***\n");
                            
                            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                                
                                System.out.println("[1] - SAVINGS ACCOUNT");
                                System.out.println("[2] - CHECKING ACCOUNT");
                                
                                BankAccount savings = null; 
                                BankAccount checking = null; 

                                for (BankAccount acc : currentCustomer.getAccounts()) {
                                    
                                    // Savings Account
                                    if (acc instanceof SavingsAccount) {
                                        String savingsAcct = acc.getAccountNumber();
                                        if (acc.getAccountNumber().equals(savingsAcct)) {
                                            savings = acc;
                                        }
                                    }
                                    
                                    // Checking Account
                                    if (acc instanceof CheckingAccount) {
                                        String checkingAcct = acc.getAccountNumber();
                                        if (acc.getAccountNumber().equals(checkingAcct)) {
                                            checking = acc;
                                        }
                                    }    
                                                
                                }
                                
                                int acctType = sc.nextInt(); 

                                if (choice == 2 || choice == 3 || choice == 4) {

                                    double amount = savings.checkAmount(sc);

                                    if (acctType == 1) { // [1] - Savings Account Type

                                            switch (choice) {
                                                case 2: // Deposit
                                                    savings.deposit(amount);
                                                    break;
                                                case 3: // Withdraw
                                                    savings.withdraw(amount);
                                                    break;
                                                case 4: // Transfer
                                                    BankAccount targetAccount = query.targetAccount(userList, sc); // account searching
                                                    savings.transfer(targetAccount, amount);
                                                default:
                                                    break;
                                            }

                                        } if (acctType == 2) { // [2] - Checking Account Type

                                            switch (choice) {
                                                case 2: // Deposit
                                                    checking.deposit(amount);
                                                    break;
                                                case 3: // Withdraw
                                                    checking.withdraw(amount);
                                                    break;
                                                case 4: // Transfer
                                                    BankAccount targetAccount = query.targetAccount(userList, sc); // account searching
                                                    checking.transfer(targetAccount, amount);
                                                default:
                                                    break;
                                            }
                                        } else {
                                            System.out.println("\n***INVALID INPUT***");
                                        }     
                                }
                                
                            } else if (choice == 5) { // History

                            } else if (choice == 6) { // Logout
                                loggedIn = false;
                            } else {
                                System.out.println("\n***INVALID INPUT***");
                            }
                                
                        // Admin Menu        
                        } else if (authenticateUser instanceof Admin) {

                        Admin adminUser = (Admin) authenticateUser;
                        System.out.println("------------------------------------------------------------\n");
                    
                        int choice = menu(sc, "\n1. View All Balance   2. View Specific Account   3. Add Fund   4. Deduct Fund   5. Logout\n", "\n***INVALID INPUT***\n");
                            
                        if (choice == 2 || choice == 3 || choice == 4) {

                            BankAccount targetAccount = query.targetAccount(userList, sc); // account searching

                            try {

                                switch (choice) {
                                    case 2: 
                                        adminUser.viewAcc(userList, targetAccount);
                                        break;
                                    case 3:
                                        targetAccount.deposit(sc.nextDouble(), targetAccount);  
                                        break;
                                    case 4:
                                        targetAccount.withdraw(sc.nextDouble(), targetAccount);
                                        break;
                                }
                            } catch (NullPointerException e) {
                                System.out.println("\n***NO ACCOUNT FOUND.***\n");
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
                //    sc.nextLine(); 
                }  
            
                }
            
        }
    }

   
    
    // User's Menu
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

    
    // Search bank account
    public BankAccount targetAccount(List<Users> userList, Scanner sc) {

        BankAccount targetAccount = null; 
        String acctNum = sc.next();

        for (Users users : userList) {
            if (users instanceof Customer) {
                Customer customers = ((Customer)users);
                // System.out.println(customers.getAccounts());
                
                for (BankAccount acc : customers.getAccounts()) {
                    if (acc.getAccountNumber().equals(acctNum)) {
                        targetAccount = acc; 
                    } 
                }
            } 
        }
        if (targetAccount == null) {
            System.out.println("\n***INVALID ACCOUNT***");        
        }
        return targetAccount;
    }
     
}



