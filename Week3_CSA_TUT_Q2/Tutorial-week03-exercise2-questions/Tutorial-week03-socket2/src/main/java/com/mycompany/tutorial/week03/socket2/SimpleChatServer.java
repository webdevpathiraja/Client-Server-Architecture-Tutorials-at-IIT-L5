/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.tutorial.week03.socket2;

// Import necessary libraries
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleChatServer {
    private static final Logger LOGGER = Logger.getLogger(SimpleChatServer.class.getName());

    public static void main(String[] args) {
        try {
            // Create a new instance of the Server Socket and pass port number
            LOGGER.info("[SERVER]Starting server on port 5000...");
            ServerSocket serverSocket = new ServerSocket(5000);
            LOGGER.info("[SERVER] is running and waiting for a client to connect");

            // Wait for a client to connect and accept the client request
            LOGGER.info("[SERVER]Waiting for client connection...");
            Socket clientSocket = serverSocket.accept();
            LOGGER.info("[SERVER]Client connected: " + clientSocket.getInetAddress());

            // Input stream to receive messages from the client
            LOGGER.info("[SERVER]Initializing input stream...");
            InputStream inputStream = clientSocket.getInputStream();
            LOGGER.info("[SERVER]Successfully initialized input stream");

            // Output stream to send messages to the client
            LOGGER.info("[SERVER]Initializing output stream...");
            OutputStream outputStream = clientSocket.getOutputStream();
            LOGGER.info("[SERVER]Output stream initialized successfully.");

            // Create a buffer array with type byte, the size must be 1024
            LOGGER.info("[SERVER]Initializing buffer for reading client messages...");
            byte[] buffer = new byte[1024];
            LOGGER.info("[SERVER]Buffer initialized with size 1024");

            LOGGER.info("[SERVER]Listening for client messages...");
            int bytesRead;

            // Read messages from the client and print them
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                LOGGER.info("[SERVER]Message received from client.");
                String message = new String(buffer, 0, bytesRead);
                LOGGER.info("Client: " + message);
                System.out.println("[SERVER]Client: " + message);

                // Send a response back to the client
                String responseMessage = "Server received your message: " + message;
                outputStream.write(responseMessage.getBytes());
                LOGGER.info("[SERVER]Response sent to client.");
            }

            // Close the sockets
            LOGGER.info("[SERVER]Closing client socket...");
            clientSocket.close();
            LOGGER.info("[SERVER]Client socket closed.");

            LOGGER.info("[SERVER]Closing server socket...");
            serverSocket.close();
            LOGGER.info("[SERVER]Server socket closed. Server shutdown complete.");
            
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "[SERVER]An error occurred: ", e);
        }
    }
}

