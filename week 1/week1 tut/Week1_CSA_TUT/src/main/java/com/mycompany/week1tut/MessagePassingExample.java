/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.week1tut;

/**
 *
 * @author ACER
 */
public class MessagePassingExample {

    public static void main(String[] args) {
        
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        
        Message message = new Message ("Hello, Receiver!");  // creating a message
        sender.sendMessage(message, receiver); // sending the message to receiver
        
    }
}
