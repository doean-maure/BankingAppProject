package com.bank.controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import com.bank.commands.Command;
import com.bank.views.ConsoleView;
import com.bank.views.InputHandler;

public class MenuController {
    private final Map<Integer, Command> menuOptions = new LinkedHashMap<>();
    private final InputHandler input;
    private final ConsoleView view;
    private boolean isRunning = true;

    public MenuController(InputHandler input, ConsoleView view) {
        this.input = input;
        this.view = view;
    }

    // New command to the menu option
    public void addCommand(int optionNumber, Command command) {
        menuOptions.put(optionNumber, command);
    }

    public void runMenu(String menuTitle) {
        isRunning = true;
        
        while (isRunning) {
            displayMenu(menuTitle);
            int choice = input.readInt("SELECT AN OPTION");

            if (menuOptions.containsKey(choice)) {
                Command selectCommand = menuOptions.get(choice);
                selectCommand.execute();
            } else if (choice == 0) {
                view.displayMessage("Exiting menu...");
                isRunning = false;
            } else {
                view.displayErrorMessage("INVALID CHOICE. Please select a valid option from the menu.");
            }
        }
    }

    private void displayMenu(String title) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(" " + title.toUpperCase());
        System.out.println("------------------------------------------------------------");

        for (Map.Entry<Integer, Command> entry : menuOptions.entrySet()) {
            view.displayMessage(entry.getKey() + ". " + entry.getValue().getDescription());
        }

        view.displayMessage("0. Exit / Logout");
        System.out.println("------------------------------------------------------------");
    }

    public void stop() {
        this.isRunning = false;
    }
}
