package dev.simmons;

import dev.simmons.entities.Client;
import dev.simmons.service.Bank;
import dev.simmons.service.Banking;
import dev.simmons.utilities.hashing.HashUtil;
import dev.simmons.utilities.logging.Logger;

import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private static final Bank bank = new Banking();
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

    }

    public boolean registerClient() {
        Client client = new Client();
        System.out.print("First, enter your name: ");
        String name = "";
        while (name.length() <= 0) {
            try {
                name = in.nextLine();
            } catch (NoSuchElementException nsee) {
                System.out.println("Please, enter your name to continue.");
            }
        }
        client.setClientName(name);

        System.out.println("Excellent, nice to meet you," + name +"!");
        System.out.print(name + ", please enter the username for your account: ");
        String username = "";
        while (username.length() <= 0) {
            try {
                username = in.nextLine();
            } catch (NoSuchElementException nsee) {
                System.out.println("Please, enter a username for the account to continue.");
            }
        }
        client.setClientUsername(username);

        System.out.println("Excellent! You'll use this to log-in for the future. Don't forget it!");
        System.out.print("Finally, please enter the password for the account: ");
        String password = "";
        while (password.length() <= 0) {
            try {
                password = in.nextLine();
            } catch (NoSuchElementException nsee) {
                System.out.println("Please, enter the password for your account to continue.");
            }
        }
        client.hashClientPassword(password);

        System.out.println("Awesome!");
        boolean result = bank.registerClient(client);
        if (result) {
            System.out.println("You're all set!");
            System.out.println("Next step: set up an account!");
        } else {
            System.out.println("Ooooh. Something went wrong. Please see an associate for help.");
        }

        return result;
    }

    public static boolean loginClient() {
        boolean result = false;
        int invalidAttempt = 0;
        System.out.println("Welcome!");
        do {
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.println("Password: ");
            String password = in.nextLine();
            Client candidate = bank.lookupClient(username);
            // Possibly try to rework this logic to handle 3 attempts of the same username?
            // Put history in a map -> login attempts?
            if (invalidAttempt > 3) {
                System.out.println("Please change your password, or see an associate for help with your account.");
                Logger.log(Logger.Level.WARNING, "Too many invalid attempts on account using " + username);
            }
            if (candidate == null || candidate.getClientSalt() == null || candidate.getClientPassword() == null) {
                System.out.println("Oops. Something went wrong. Please confirm your username and password, and try again.");
                invalidAttempt++;
                continue;
            }
            String hashedPassword = HashUtil.hashSaltedString(password, candidate.getClientSalt());

            if (!hashedPassword.equals(candidate.getClientPassword())) {

            }
        } while (invalidAttempt <= 3);

        return result;
    }
}
