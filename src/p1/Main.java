package p1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// User class to represent each user of the ATM
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

// ATM class encapsulating ATM functionalities
class ATM {
    private Map<String, User> users;

    public ATM() {
        users = new HashMap<>();
        // Example users
        users.put("123456", new User("123456", "1234", 1000.0));
        users.put("789012", new User("789012", "5678", 500.0));
    }

    // Method for user authentication
    public User authenticateUser(String userID, String userPIN) {
        if (users.containsKey(userID)) {
            User user = users.get(userID);
            if (user.getUserPIN().equals(userPIN)) {
                return user;
            }
        }
        return null;
    }

    // Method for checking balance
    public void checkBalance(User user) {
        System.out.println("Your account balance is: $" + user.getAccountBalance());
    }

    // Method for withdrawing money
    public void withdraw(User user, double amount) {
        if (amount > 0 && amount <= user.getAccountBalance()) {
            user.setAccountBalance(user.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + user.getAccountBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    // Method for depositing money
    public void deposit(User user, double amount) {
        if (amount > 0) {
            user.setAccountBalance(user.getAccountBalance() + amount);
            System.out.println("Deposit successful. New balance: $" + user.getAccountBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM Interface program!");

        // User authentication
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        User currentUser = atm.authenticateUser(userID, userPIN);
        if (currentUser == null) {
            System.out.println("Invalid user ID or PIN. Exiting...");
            return;
        }

        System.out.println("Authentication successful.");

        // Main menu
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance(currentUser);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(currentUser, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(currentUser, depositAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
