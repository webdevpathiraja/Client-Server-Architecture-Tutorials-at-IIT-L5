/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week2tut;

/**
 *
 * @author ACER
 */
public class User {
    private String name;
    private int age;
    
    public User (String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int age() {
        return age;
    }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";  // Formatted string representation of the User object
    }
}
