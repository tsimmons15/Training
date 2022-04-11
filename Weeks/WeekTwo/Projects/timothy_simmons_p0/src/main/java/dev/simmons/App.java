package dev.simmons;

import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.service.Bank;
import dev.simmons.service.Banking;
import dev.simmons.utilities.hashing.HashUtil;
import dev.simmons.utilities.lists.List;
import dev.simmons.utilities.logging.Logger;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private static final Bank bank = new Banking();
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        outerMenu();
        System.out.println("Until next time! Bis dann!");
    }

    public static void outerMenu() {
        int choice = 0;
        do {
            choice = mainMenuPrompt();
            switch (choice) {
                case 1: // Login to existing account
                    Client curr = loginClient();
                    userMenu(curr);
                    break;
                case 2: // New User
                    registerClient();
                    break;
                case 3: // Contact Info!
                    System.out.println("We would love to hear from you!");
                    System.out.println("Our business hours are:");
                    System.out.println(" - Monday    10:00AM - 12:00PM, 2:00PM - 5:00PM");
                    System.out.println(" - Tuesday   10:00AM - 12:00PM, 2:00PM - 5:00PM");
                    System.out.println(" - Wednesday 10:00AM - 12:00PM, 2:00PM - 5:00PM");
                    System.out.println(" - Thursday  10:00AM - 12:00PM, 2:00PM - 5:00PM");
                    System.out.println(" - Friday    10:00AM - 12:00PM");
                    System.out.println(" - Saturday   9:00AM - 11:00PM");
                    System.out.println("Or, you can call our toll-free help-line: 1-800-669-2942!");
                    System.out.println("Our customer service help email: help@bank.com");
                    break;
                case 4: // Exit
                    System.out.println("We would love to hear from you! Please fill out our survey!");
                    break;
            }
        } while (choice != 4);
    }

    public static void userMenu(Client client) {
        int choice = -1;
        do {
            choice = userMenuPrompt(client);

            switch (choice) {
                case 1:
                    createAccount(client);
                    break;
                case 2:
                    showAccountDetails(client);
                    break;
                case 3:
                    handleTransaction(client);
            }
        } while (choice != 4);
    }

    private static int mainMenuPrompt() {
        int choice = -1;

        do {
            System.out.println("1.) Login to existing account!");
            System.out.println("2.) Create new account!");
            System.out.println("3.) Contact Us!");
            System.out.println("4.) Exit");
            System.out.print("> ");
            try {
                choice = in.nextInt();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Please enter a valid option!");
            }
        } while (choice < 1 || choice > 3);

        return choice;
    }
    private static int userMenuPrompt(Client client) {
        int choice = -1;

        System.out.println("Greetings, " + client.getClientName() + "! Welcome back!");
        do {
            System.out.println("1.) Create Account!");
            System.out.println("2.) View Account Details!");
            System.out.println("3.) Transactions!");
            System.out.println("4.) Return");
            System.out.print("> ");
            try {
                choice = in.nextInt();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Please enter a valid option!");
            }
        } while (choice < 1 || choice > 4);

        return choice;
    }

    public static boolean registerClient() {
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
            System.out.println("Oops! Something went wrong. Please see an associate for help.");
            Logger.log(Logger.Level.INFO, "Issue creating user for: " + client.getDiagnostics());
        }

        return result;
    }

    public static Client loginClient() {
        int invalidAttempt = 0;
        Client candidate = null;
        System.out.println("Welcome!");
        do {
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.println("Password: ");
            String password = in.nextLine();
            candidate = bank.lookupClient(username);
            // Possibly try to rework this logic to handle 3 attempts of the same username?
            // Put history in a map -> login attempts?
            if (invalidAttempt > 3) {
                System.out.println("Please change your password, or see an associate for help with your account.");
                Logger.log(Logger.Level.WARNING, "Too many invalid attempts on account using " + username);
                candidate = null;
                break;
            }
            if (candidate == null || candidate.getClientSalt() == null || candidate.getClientPassword() == null) {
                System.out.println("Oops. Something went wrong. Please confirm your username and password, and try again.");
                invalidAttempt++;
                candidate = null;
                continue;
            }
            String hashedPassword = HashUtil.hashSaltedString(password, candidate.getClientSalt());

            if (!hashedPassword.equals(candidate.getClientPassword())) {
                System.out.println("Oops. Something went wrong. Please confirm your username and password, and try again.");
                candidate = null;
                continue;
            }
        } while (candidate == null && invalidAttempt <= 3);

        return candidate;
    }

    public static boolean createAccount(Client client) {
        boolean result = false;

        System.out.println("So, which kind of account would you like to create today, " + client.getClientName() + "?");
        int choice = -1;
        String name = "";
        do {
            System.out.println("1.) " + Account.AccountType.Checking.name());
            System.out.println("2.) " + Account.AccountType.Savings.name());
            System.out.println("3.) " + Account.AccountType.CD.name());
            System.out.print("> ");

            try {
                choice = in.nextInt();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Please enter one of the valid values.");
                choice = -1;
            }
        } while (choice < 1 || choice > 3);

        Account account = Account.accountFactory(choice);
        result = bank.createAccount(account, client);
        if (result) {
            System.out.println("Great! Your " + account.getType().name() + " account is all set!");
        } else {
            System.out.println("Oops! Something went wrong... Please see an associate for assistance!");
            Logger.log(Logger.Level.INFO, "Issue creating account: " + account.getDiagnostics());
            Logger.log(Logger.Level.INFO, "Issue creating account for: " + client.getDiagnostics());
        }

        return result;
    }

    public static void showAccountDetails(Client client) {
        if (client == null) {
            Logger.log(Logger.Level.WARNING, "Unauthorized access to accounts.");
            return;
        }

        List<Account> accounts = bank.getAccountInfo(client.getClientId());

        System.out.println(String.format("Accounts for: %s (%s)", client.getClientName(), client.getClientUsername()));
        for (Account a : accounts) {
            System.out.println(a);
        }
    }
    public static void handleTransaction(Client client) {
        String cont = "";

        do {
            int choice = getTransactionType();

            switch(choice) {
                case 1:
                    handleWithdraw(client);
                    break;
                case 2:
                    handleDeposit(client);
                    break;
                case 3:
                    handleTransfer(client);
                    break;
            }
            System.out.print("Another transaction? [Y/N] ");
            cont = in.nextLine().toLowerCase();
        } while (cont.startsWith("y"));
    }
    private static int getTransactionType() {
        int choice = 0;
        do {
            System.out.println("1.) Withdraw");
            System.out.println("2.) Deposit");
            System.out.println("3.) Transfer");
            System.out.println("4.) Return");
            System.out.println("> ");

            try {
                choice = in.nextInt();
            } catch(NoSuchElementException nsee) {
                System.out.println("Oops! Please enter one of the available choices!");
            }
        } while (choice < 1 || choice > 4);

        return choice;
    }

    private static void handleWithdraw(Client client) {
        List<Account> accounts = bank.getAccountInfo(client.getClientId());
        int choice = 0;
        do {
            for(int i = 0; i < accounts.length(); i++) {
                System.out.println((i+1) + ".) " + accounts.get(i));
            }
            System.out.print("From which account would you like to withdraw? ");
            try {
                choice = in.nextInt();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Please select one of the above options.");
            }
        } while (choice < 1 || choice > accounts.length());

        System.out.println("Excellent!");

        Account account = accounts.get(choice-1);
        double amount = -1;
        do {
            System.out.println(account);
            System.out.print("How much would you like to withdraw? $");
            try {
                amount = in.nextDouble();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Make sure you enter a dollar amount!");
            }
        } while (amount < 0 && amount > account.getBalance());

        bank.withdraw(account, amount);
    }

    private static void handleDeposit(Client client) {
        List<Account> accounts = bank.getAccountInfo(client.getClientId());
        int choice = 0;
        do {
            for(int i = 0; i < accounts.length(); i++) {
                System.out.println((i+1) + ".) " + accounts.get(i));
            }
            System.out.print("To which account would you like to deposit? ");
            try {
                choice = in.nextInt();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Please select one of the above options.");
            }
        } while (choice < 1 || choice > accounts.length());

        System.out.println("Excellent!");

        Account account = accounts.get(choice-1);
        double amount = -1;
        do {
            System.out.println(account);
            System.out.print("How much would you like to deposit? $");
            try {
                amount = in.nextDouble();
            } catch (NoSuchElementException nsee) {
                System.out.println("Oops! Make sure you enter a dollar amount!");
            }
        } while (amount < 0 && amount > account.getBalance());

        bank.deposit(account, amount);
    }

    private static void handleTransfer(Client client) {

    }
}
