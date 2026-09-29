import com.bank.controllers.CustomerController;
import com.bank.models.*;
import com.bank.repository.InMemoryUserRepository;
import com.bank.repository.UserRepository;
import com.bank.views.*;

public class BankingApp {

    private final InputHandler input = new InputHandler();
    private final ConsoleView view = new ConsoleView();
    private final UserRepository userRepository = new InMemoryUserRepository();
    
    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        app.start(); 
    }
    
    public void start() {

        boolean cont = true;
       
        // Login
        while (cont) {
            view.displayHeader("ACCOUNT LOGIN.");
            String mobileInput = input.readString("MOBILE NUMBER");
            int pinInput = input.readInt("PIN");
            Users authenticateUser = userRepository.findByMobileAndPin(mobileInput, pinInput);
            
            CustomerController customerController = new CustomerController(input, view, userRepository);
            
            if (authenticateUser != null) {
                if ("CUSTOMER".equalsIgnoreCase(authenticateUser.getRole())) {
                    customerController.startSession((Customer)authenticateUser);
                } else if ("ADMIN".equalsIgnoreCase(authenticateUser.getRole())) {

                }
            } else {
                view.displayErrorMessage("INCORRECT MOBILE NUMBER OR PIN.");
            }
        } 
    }
}



