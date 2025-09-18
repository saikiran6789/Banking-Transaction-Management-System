/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (atmSystem.authenticateUser(userId, pin)) {
            atmSystem.showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}

