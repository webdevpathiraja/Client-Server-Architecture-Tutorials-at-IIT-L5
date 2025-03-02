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
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* **Define a public class named SimpleChatClient. This class will contain all the logic for our chat client program.** */
public class SimpleChatClient {
    private static final Logger logger = Logger.getLogger(SimpleChatClient.class.getName());

    /* **Define the main method. This is the entry point for any Java application.** */
    public static void main(String[] args) {

        /* **Try to establish a connection to the server and handle communication with the server. 
        If an IOException occurs, it will be caught and handled in the catch block.** */
        try {

            /* **Create a new Socket object to connect to the server. 
            The Socket constructor takes two parameters: the server's IP address (or hostname) and the port number.** */
            logger.info("Attempting to connect to the server at localhost:5000...");
            Socket socket = new Socket("localhost", 5000);
            logger.info("Successfully connected to the server.");

            /* **Print a message to the console indicating that the client has connected to the server.** */
            System.out.println("Connected to the server.");

            /* **Get the input stream of the socket. This stream is used to receive data from the server.** */
            logger.info("Retrieving input stream...");
            InputStream inputStream = socket.getInputStream();
            logger.info("Input stream successfully retrieved.");

            /* **Get the output stream of the socket. This stream is used to send data to the server.** */
            logger.info("Retrieving output stream...");
            OutputStream outputStream = socket.getOutputStream();
            logger.info("Output stream successfully retrieved.");

            /* **Create a Scanner object to read input from the user. The Scanner constructor takes an input stream as a parameter. 
            Here, we pass System.in to read input from the console.** */
            logger.info("Initializing scanner for user input...");
            Scanner scanner = new Scanner(System.in);
            logger.info("Scanner initialized successfully.");

            /* **Enter an infinite loop where the client will continuously send messages to the server and receive responses.** */
            while (true) {

                /* **Prompt the user to enter a message.** */
                logger.info("Prompting user for input...");
                System.out.print("You: ");

                /* **Read a line of input from the user.** */
                String message = scanner.nextLine();
                logger.info("User input received: " + message);

                /* **Send the user's message to the server. The write method of the OutputStream class is used here. 
                It takes a byte array as a parameter, so we call the getBytes method of the String class to convert the message to bytes.** */
                logger.info("Sending message to server...");
                outputStream.write(message.getBytes());
                logger.info("Message sent successfully.");

                /* **Create a byte array to store the server's response. you may name it "buffer"** */
                logger.info("Waiting for server response...");
                byte[] buffer = new byte[1024];

                /* **Read the server's response into the buffer. The read method of the InputStream class is used here. 
                It reads data from the input stream into the buffer and returns the number of bytes read.** */
                int bytesRead = inputStream.read(buffer);
                logger.info("Response received from server.");

                /* **Convert the server's response to a string. The String constructor is used here. 
                It takes three parameters: the byte array containing the data, the offset to start at (0 in this case), and the number of bytes to convert.** */
                String responseMessage = new String(buffer, 0, bytesRead);
                logger.info("Server response processed: " + responseMessage);

                /* **Print the server's response to the console.** */
                System.out.println("Server: " + responseMessage);
            }

        /* **Catch any IOException that may occur and print the stack trace. 
            An IOException is thrown when an input-output operation is failed or interrupted.** */
        } catch (SocketException e) {
            logger.log(Level.SEVERE, "SocketException occurred!", e);
        } catch (SocketTimeoutException e) {
            logger.log(Level.SEVERE, "SocketTimeoutException occurred!", e);
        } catch (IllegalBlockingModeException e) {
            logger.log(Level.SEVERE, "IllegalBlockingModeException occurred!", e);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "SecurityException occurred!", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occurred!", e);
        }
    }
}