/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket1;

/** Import the BufferedReader class, which reads text from a character-input stream, 
 * buffering characters so as to provide for the efficient reading of characters, arrays, and lines. */
import java.io.BufferedReader;

/* **Import the IOException class, which is thrown when an input-output operation is failed or interrupted.** */
import java.io.IOException;

/* **Import the InputStreamReader class, which is a bridge from byte streams to 
 * character streams. It reads bytes and decodes them into characters using a specified charset.** */
import java.io.InputStreamReader;

/* **Import the PrintWriter class, which prints formatted representations of objects to a text-output stream.** */
import java.io.PrintWriter;

/* **Import the Socket class, which is used for client-side TCP operations. 
 * It creates a socket, connects it to a specified port number at a specified IP address.** */
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;

/* **Define a public class named Client. This class will contain all the logic for our client program.** */
public class Client {

    /* **Define the main method. This is the entry point for any Java application.** */
    public static void main(String[] args) {

        /* **Define a final string variable for the server address. 
         * "localhost" is used here, which means the server is running on the same machine as the client.** */
        final String localhost = "localhost";

        /* **Define a final integer variable for the server port. 12345 is used here. 
         * The client will connect to this port on the server.** */
        final Integer serverPort = 12345;

        /* **Try to establish a socket connection to the server. 
         * The Socket class constructor takes two parameters: the server address and the server port. The try-with-resources statement ensures that the socket is closed when it is no longer needed.** */
        try (Socket socket = new Socket(localhost, serverPort)) {

            /* **Create a PrintWriter object for sending messages to the server. 
             * The PrintWriter class constructor takes two parameters: the socket output stream and 
             * a boolean indicating whether to automatically flush the output stream after every write operation.** */
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            /* **Create a BufferedReader object for reading server responses. 
             * The BufferedReader class constructor takes an InputStreamReader, which in turn takes the socket input stream.** */
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /* **Send a message to the server. The PrintWriter's println method is used here, 
             * which sends a string followed by a newline to the server.** */
            out.println("[CLIENT]Hello to Server from Client!");

            /* **Read the server response and store it in a string variable. 
             * The BufferedReader's readLine method is used here, which reads a line of text from the server.** */
            String serverResponse = in.readLine();

            /* **Print the server response to the console. The System.out.println method is used here, 
             * which prints a string followed by a newline to the console.** */
            System.out.println("Server Response: " + serverResponse);

        /* **Catch specific exceptions that may occur and print the stack trace.** */
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IllegalBlockingModeException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


