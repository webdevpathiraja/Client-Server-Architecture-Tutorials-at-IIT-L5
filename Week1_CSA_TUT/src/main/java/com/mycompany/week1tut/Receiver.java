/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week1tut;

/**
 *
 * @author ACER
 */
public class Receiver {
    // Method to receive and process a message
    public void receiveMessage(Message message) {
        System.out.println("Receiver: Message received.");
        System.out.println("Message Content: " + message.getContent());
    }
}

