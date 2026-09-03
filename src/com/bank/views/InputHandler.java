package com.bank.views;

import java.util.Scanner;

public class InputHandler {
    private final Scanner sc;

    public InputHandler() {
        this.sc = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); // Clear buffer
                return value;
            }
            System.out.println("\n*** INVALID INPUT (INTEGER REQUIRED) ***\n");
            sc.nextLine(); // Clear invalid input
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();
                sc.nextLine(); // Clear buffer
                return value;
            }
            System.out.println("\n*** INVALID AMOUNT ***\n");
            sc.nextLine(); // Clear invalid input
        }
    }
}
