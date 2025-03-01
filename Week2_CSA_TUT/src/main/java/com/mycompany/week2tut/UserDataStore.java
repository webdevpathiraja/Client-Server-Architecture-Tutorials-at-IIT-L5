/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week2tut;

/**
 *
 * @author ACER
 */
import java.util.ArrayList;
import java.util.List;

public class UserDataStore {
    private List<User> userList;
    
    public UserDataStore() {
        userList = new ArrayList<>();
    }
    
    public void addUser(User user) {
        userList.add(user);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }
}
