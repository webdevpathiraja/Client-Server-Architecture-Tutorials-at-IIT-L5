/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week1tut;

/**
 *
 * @author ACER
 */
public class Sender {
    // method to create a message
    public Message createMessage(String content) {
        Message message = new Message(content);
        System.out.println("Message created: " + message.getContent());
        return message;
    }

    // method to send a message to a receiver
    public void sendMessage(Message message, Receiver receiver) {
        System.out.println("Sending message to receiver...");
        receiver.receiveMessage(message);
    }
}

