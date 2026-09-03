import com.bank.models.*;
import com.bank.views.*;
import java.util.List;

public class BankingApp {

    private final InputHandler input = new InputHandler();
    private final ConsoleView view = new ConsoleView();
    
    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        app.start(); 
    }
    
    public void start() {

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
            view.displayHeader("ACCOUNT LOGIN.");
            String mobileInput = input.readString("MOBILE NUMBER");
            int pinInput = input.readInt("PIN");

            for (Users u : userList) { 
                if (u.getMobileNum().equals(mobileInput) && u.getPin() == pinInput) {
                    authenticateUser = u;
                    loggedIn = true;
                    break;
                } 
            }

            if (!loggedIn) {
                view.displayErrorMessage("INCORRECT MOBILE NUMBER OR PIN.");
            }
            
                // User Logged In
                while (loggedIn) {
                
                    if (authenticateUser != null) {

                        BankingApp query = new BankingApp();

                        // Customer Menu
                        if (authenticateUser instanceof Customer) { 
                            Customer currentCustomer = (Customer) authenticateUser;
                            view.displayHeader("1. Check Balance  2. Deposit  3. Withdraw  4. Transfer Money   5. History  6. Logout");
                            int choice = input.readInt("CHOICE");
                            
                            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                                
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
                                
                                int acctType = input.readInt("[1]SAVINGS ACCOUNT - [2]CHECKING ACCOUNT"); 

                                if (choice == 2 || choice == 3 || choice == 4) {

                                    double amount = input.readDouble("AMOUNT");

                                    if (acctType == 1) { // [1] - Savings Account Type

                                            switch (choice) {
                                                case 2: // Deposit
                                                    savings.deposit(amount);
                                                    break;
                                                case 3: // Withdraw
                                                    savings.withdraw(amount);
                                                    break;
                                                case 4: // Transfer
                                                    try {
                                                        String mobileInputTarget = input.readString("MOBILE NUMBER");
                                                        BankAccount targetAccount = query.targetAccount(userList, mobileInputTarget); // account searching
                                                        savings.transfer(targetAccount, amount);
                                                    } catch (NullPointerException e) {
                                                        view.displayErrorMessage("INVALID ACCOUNT.");
                                                    }
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
                                                    try {
                                                        String mobileInputTarget = input.readString("MOBILE NUMBER");
                                                        BankAccount targetAccount = query.targetAccount(userList, mobileInputTarget); // account searching
                                                        checking.transfer(targetAccount, amount);
                                                    } catch (NullPointerException e) {
                                                        view.displayErrorMessage("INVALID ACCOUNT.");
                                                    }
                                                default:
                                                    break;
                                            }
                                        }    
                                } else if (choice == 1 && acctType == 1) {
                                    view.displayMessage(savings.getAccountType());
                                    System.out.println(savings.getBalance());
                                } else if (choice == 1 && acctType == 2){
                                    view.displayMessage(checking.getAccountType());
                                    System.out.println(checking.getBalance());
                                }
                                
                            } else if (choice == 5) { // History

                            } else if (choice == 6) { // Logout
                                loggedIn = false;
                            }
                                
                        // Admin Menu        
                        } else if (authenticateUser instanceof Admin) {

                        Admin adminUser = (Admin) authenticateUser;
                        view.displayHeader("1. View All Balance   2. View Specific Account   3. Add Fund   4. Deduct Fund   5. Logout");
                        int choice = input.readInt("CHOICE");
                                                
                        if (choice == 2 || choice == 3 || choice == 4) {

                            String mobileInputTarget = input.readString("MOBILE NUMBER");
                            BankAccount targetAccount = query.targetAccount(userList, mobileInputTarget); // account searching

                            try {

                                switch (choice) {
                                    case 2: 
                                        adminUser.viewAcc(userList, targetAccount);
                                        break;
                                    case 3:
                                        targetAccount.deposit(input.readDouble("AMOUNT"), targetAccount);  
                                        break;
                                    case 4:
                                        targetAccount.withdraw(input.readDouble("AMOUNT"), targetAccount);
                                        break;
                                }
                            } catch (NullPointerException e) {
                                view.displayErrorMessage("NO ACCOUNT FOUND.");
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
    
    // Search bank account
    public BankAccount targetAccount(List<Users> userList, String mobileInputTarget) {

        BankAccount targetAccount = null; 

        for (Users users : userList) {
            if (users instanceof Customer) {
                Customer customers = ((Customer)users);
                for (BankAccount acc : customers.getAccounts()) {
                    if (acc.getAccountNumber().equals(mobileInputTarget)) {
                        targetAccount = acc; 
                    } 
                }
            }
        }

        return targetAccount;
    }
     
}



