/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket2;

/* **Import necessary packages */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/* **Define a public class named SimpleChatClient. This class will contain all the logic for our chat client program.** */
public class SimpleChatClient {

    /* **Define the main method. This is the entry point for any Java application.** */
    public static void main(String[] args) {

        /* **Try to establish a connection to the server and handle communication with the server. 
        If an IOException occurs, it will be caught and handled in the catch block.** */
        try {

            /* **Create a new Socket object to connect to the server. 
            The Socket constructor takes two parameters: the server's IP address (or hostname) and the port number.** */
            Socket socket = new Socket("localhost", 5000);

            /* **Print a message to the console indicating that the client has connected to the server.** */
            System.out.println("Connected to the server.");

            /* **Get the input stream of the socket. This stream is used to receive data from the server.** */
            InputStream inputStream = socket.getInputStream();

            /* **Get the output stream of the socket. This stream is used to send data to the server.** */
            OutputStream outputStream = socket.getOutputStream();

            /* **Create a Scanner object to read input from the user. The Scanner constructor takes an input stream as a parameter. 
            Here, we pass System.in to read input from the console.** */
            Scanner scanner = new Scanner(System.in);

            /* **Enter an infinite loop where the client will continuously send messages to the server and receive responses.** */
            while (true) {

                /* **Prompt the user to enter a message.** */
                System.out.print("You: ");

                /* **Read a line of input from the user.** */
                String message = scanner.nextLine();

                /* **Send the user's message to the server. The write method of the OutputStream class is used here. 
                It takes a byte array as a parameter, so we call the getBytes method of the String class to convert the message to bytes.** */
                outputStream.write(message.getBytes());

                /* **Create a byte array to store the server's response. you may name it "buffer"** */
                byte[] buffer = new byte[1024];

                /* **Read the server's response into the buffer. The read method of the InputStream class is used here. 
                It reads data from the input stream into the buffer and returns the number of bytes read.** */
                int bytesRead = inputStream.read(buffer);

                /* **Convert the server's response to a string. The String constructor is used here. 
                It takes three parameters: the byte array containing the data, the offset to start at (0 in this case), and the number of bytes to convert.** */
                String responseMessage = new String(buffer, 0, bytesRead);

                /* **Print the server's response to the console.** */
                System.out.println("Server: " + responseMessage);
            }

        /* **Catch any IOException that may occur and print the stack trace. 
            An IOException is thrown when an input-output operation is failed or interrupted.** */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}