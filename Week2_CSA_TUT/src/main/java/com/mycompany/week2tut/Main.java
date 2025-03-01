/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.week2tut;

/**
 *
 * @author ACER
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Setting Up
        // 1.1 Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        // 1.2 Create instances of UserValidator and UserDataStore
        UserValidator validator = new UserValidator();
        UserDataStore dataStore = new UserDataStore();
        
        // 1.3 Create a List to store Thread objects
        List<Thread> threadList = new ArrayList<>();
        
        // 1.4 Create a lock object for synchronisation
        Object lock = new Object();
        
        // 1.5 Initialise a boolean variable to control the input loop
        boolean addMoreUsers = true;
        
        // 2. User Input Loop
        while (addMoreUsers) {
            // 7. Simulate concurrent user input (3 users at a time using for loop)
            for (int i = 0; i < 3; i++) {
                // 8. Prompt the user to enter a name
                System.out.print("Enter user name: ");
                // 9. Read the name from the input
                String name = scanner.nextLine();
                
                // 10. Prompt the user to enter an age
                System.out.print("Enter user age: ");
                
                // 11. Read the age from the input with exception handling
                int age = -1; // Default invalid age
                boolean validInput = false;
                
                while (!validInput) {
                    try {
                        age = scanner.nextInt();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        // Clear the invalid input
                        scanner.next();
                        System.out.println("Invalid input. Age must be a number.");
                        System.out.print("Enter user age again: ");
                    }
                }
                
                // 12. Create a UserProcessor object
                UserProcessor processor = new UserProcessor(validator, dataStore, lock, name, age);
                
                // 13. Create a Thread object with the UserProcessor object
                Thread t = new Thread(processor);
                
                // 14. Add the thread to the thread list
                threadList.add(t);
                
                // 15. Consume the newline character left by nextInt()
                scanner.nextLine();
            }
            
            // 16. Ask the user if they want to add more users
            System.out.print("Do you want to add more users? (yes/no): ");
            
            // 17. Read the user's response using nextLine()
            String response = scanner.nextLine();
            
            // 18. Update the loop control variable based on the response
            addMoreUsers = response.equalsIgnoreCase("yes");
        }
        
        // 3. Thread Execution
        // 19. Iterate through the thread list and start/join each thread
        for (Thread t : threadList) {
            // 20. Start the thread
            t.start();
            
            try {
                // 21. Wait for the thread to finish (join)
                t.join();
            } catch (InterruptedException e) {
                // 21.1 Handle potential InterruptedException
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
        }
        
        // 4. Displaying Users
        // 22. Display all users stored in the data store
        System.out.println("\nAll Users:");
        for (User user : dataStore.getAllUsers()) {
            System.out.println(user);
        }
        
        // Close the scanner
        scanner.close();
    }
}
