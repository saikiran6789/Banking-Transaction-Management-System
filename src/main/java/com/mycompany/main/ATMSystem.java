/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.HashMap;
import java.util.Scanner;

public class ATMSystem {
    private HashMap<String, Account> accounts;
    private Account currentAccount;

    public ATMSystem() {
        accounts = new HashMap<>();
        accounts.put("user1", new Account("user1", "1111", 5000));
        accounts.put("user2", new Account("user2", "2222", 3000));
    }

    public boolean authenticateUser(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.checkPin(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transactions History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + currentAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (currentAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. New balance: $" + currentAccount.getBalance());
                    } else {
                        System.out.println("Insufficient funds or invalid amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: $" + currentAccount.getBalance());
                    break;
                case 4:
                    System.out.print("Enter user ID to transfer to: ");
                    String toUserId = scanner.next();
                    Account toAccount = accounts.get(toUserId);
                    if (toAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        if (currentAccount.transfer(toAccount, transferAmount)) {
                            System.out.println("Transfer successful. New balance: $" + currentAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds or invalid amount.");
                        }
                    } else {
                        System.out.println("Invalid user ID.");
                    }
                    break;
                case 5:
                    System.out.println("Transaction History:");
                    for (Transaction transaction : currentAccount.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 6);
    }
}
