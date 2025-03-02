/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket1;

/* **Import the BufferedReader class, which reads text from a character-input stream, 
buffering characters so as to provide for the efficient reading of characters, arrays, and lines.** */
import java.io.BufferedReader;

/* **Import the IOException class, which is thrown when an input-output operation is failed or interrupted.** */
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;

/* **Import the InputStreamReader class, which is a bridge from byte streams to character streams. 
It reads bytes and decodes them into characters using a specified charset.** */
import java.io.InputStreamReader;

/* **Import the PrintWriter class, which prints formatted representations of objects to a text-output stream.** */
import java.io.PrintWriter;

/* **Import the ServerSocket class, which provides a system-independent implementation of the server side of 
    a client/server socket connection.** */
import java.net.ServerSocket;

/* **Import the Socket class, which is used for client-side TCP operations. 
It creates a socket, connects it to a specified port number at a specified IP address.** */
import java.net.Socket;

/* **Import logging classes for logging server operations** */
import java.util.logging.Level;
import java.util.logging.Logger;

/* **Define a public class named Server. This class will contain all the logic for our server program.** */
public class Server {
    
    /* **Initialize a Logger for logging messages** */
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    
    /* **Define the main method. This is the entry point for any Java application. 
    The Java Virtual Machine (JVM) calls the main method when the program starts.** */
    public static void main (String[] args) {
        
        /* **Define a final integer variable for the port number. 12345 is used here. 
        The server will listen on this port for incoming client connections.** */
        final Integer serverPort = 12345;
        logger.info("[SERVER] Server port defined as: " + serverPort);

        logger.info("[SERVER] Starting server initialization...");
        
        /* **Try to establish a server socket on the specified port number. 
        The ServerSocket class constructor takes one parameter: the port number. The try-with-resources statement ensures that the server socket is closed when it is no longer needed.** */
        logger.info("[SERVER] About to create ServerSocket on port " + serverPort);
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            logger.info("[SERVER] ServerSocket created successfully on port " + serverPort);
            
            /* **Log a message indicating that the server is listening on the specified port.** */
            logger.info("[SERVER] About to print server listening message to console");
            System.out.println("[SERVER] Server is listening on port " + serverPort);
            logger.info("[SERVER] Server listening message printed to console");

            /* **Enter an infinite loop where the server will continuously listen for and accept incoming client connections.** */
            logger.info("[SERVER] About to enter server loop to accept client connections");
            while (true) {
                logger.info("[SERVER] Inside server loop iteration");
                
                /* **Accept an incoming client connection. The accept method of the ServerSocket class blocks
                until a connection is made, and then returns a new Socket object representing the client connection.** */
                logger.info("[SERVER] About to call serverSocket.accept()");
                Socket clientSocket = serverSocket.accept();
                logger.info("[SERVER] serverSocket.accept() returned successfully with client connection");

                /* **Log a message indicating that a client has connected. 
                The getInetAddress method of the Socket class returns the IP address of the client.** */
                logger.info("[SERVER] About to get client IP address");
                String clientAddress = clientSocket.getInetAddress().toString();
                logger.info("[SERVER] Client IP address retrieved: " + clientAddress);
                
                logger.info("[SERVER] About to print client connected message to console");
                System.out.println("[SERVER] Client connected: " + clientAddress);
                logger.info("[SERVER] Client connected message printed to console");

                /* **Create a BufferedReader object for reading incoming messages from the client. 
                The BufferedReader class constructor takes an InputStreamReader, which in turn takes the client socket's input stream.** */
                logger.info("[SERVER] About to get input stream from client socket");
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                logger.info("[SERVER] Input stream obtained from client socket");
                
                logger.info("[SERVER] About to create BufferedReader from InputStreamReader");
                BufferedReader in = new BufferedReader(inputStreamReader);
                logger.info("[SERVER] BufferedReader created successfully");

                /* **Create a PrintWriter object for sending messages to the client. 
                The PrintWriter class constructor takes two parameters: the client socket's output stream 
                and a boolean indicating whether to automatically flush the output stream after every write operation.** */
                logger.info("[SERVER] About to get output stream from client socket");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                logger.info("[SERVER] PrintWriter created successfully with autoFlush=true");

                /* **Read a message from the client and store it in a string variable. 
                The readLine method of the BufferedReader class reads a line of text from the client.** */
                logger.info("[SERVER] About to call readLine() to receive client message");
                String clientMessage = in.readLine();
                logger.info("[SERVER] readLine() returned with client message");

                /* **Print the client's message to the console.** */
                logger.info("[SERVER] About to process received client message: " + clientMessage);
                logger.info("[SERVER] About to print received message to console");
                System.out.println("[SERVER] Received from client: " + clientMessage);
                logger.info("[SERVER] Received message printed to console");

                /* **Send a response back to the client. The println method of the PrintWriter class 
                sends a string followed by a newline to the client.** */
                logger.info("[SERVER] About to create response message");
                String serverResponse = "[SERVER] Message received: " + clientMessage;
                logger.info("[SERVER] Response message created: " + serverResponse);
                
                logger.info("[SERVER] About to send response to client");
                out.println(serverResponse);
                logger.info("[SERVER] Response sent to client successfully");

                /* **Close the client socket. The close method of the Socket class closes the client socket and 
                releases all associated resources.** */
                clientSocket.close();
                logger.info("[SERVER] Client socket closed successfully");
                
                logger.info("[SERVER] End of loop iteration, continuing to next iteration");
            }

        /* **Catch specific exceptions that may occur and log them with appropriate levels.** */
        } catch (SocketException e) {
            logger.log(Level.SEVERE, "[SERVER] SocketException occurred!", e);
        } catch (SocketTimeoutException e) {
            logger.log(Level.SEVERE, "[SERVER] SocketTimeoutException occurred!", e);
        } catch (IllegalBlockingModeException e) {
            logger.log(Level.SEVERE, "[SERVER] IllegalBlockingModeException occurred!", e);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "[SERVER] SecurityException occurred!", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "[SERVER] IOException occurred!", e);
            logger.info("[SERVER] About to print stack trace");
            e.printStackTrace();
            logger.info("[SERVER] Stack trace printed");
        }
        
        logger.info("[SERVER] Exiting main method");
    }
}