/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week2tut;

/**
 *
 * @author ACER
 */
public class UserProcessor implements Runnable {
    private UserValidator validator;
    private UserDataStore dataStore;
    private Object lock;
    private String name;
    private int age;
    
    public UserProcessor(UserValidator validator, UserDataStore dataStore, Object lock, String name, int age) {
        this.validator = validator;
        this.dataStore = dataStore;
        this.lock = lock;
        this.name = name;
        this.age = age;
    }
    
    @Override
    public void run() {
        // Process user data
        if (validator.isValidAge(age)) {
            synchronized(lock) {
                User user = new User(name, age);
                dataStore.addUser(user);
                System.out.println("Added user: " + user);
            }
        } else {
            System.out.println("Invalid age for user: " + name);
        }
    }
}
