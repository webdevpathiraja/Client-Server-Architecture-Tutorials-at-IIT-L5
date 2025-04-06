/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab.based.practical.mock.solutions;


import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class ClientServer {

    // Logger instance for logging information and errors
    private static final Logger logger = Logger.getLogger(ClientServer.class.getName());

    // Port number the server listens on
    private static final int PORT = 12345;

    // Main method: entry point of the application
    public static void main(String[] args) {
        // Start the server thread
        new Thread(new Server()).start();
        // Start the client thread
        new Thread(new Client()).start();
    }

    // Nested Server class that implements Runnable to handle server logic
    static class Server implements Runnable {
        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                logger.info("Server started on port " + PORT);
                // Loop to accept incoming client connections
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    logger.info("Client connected: " + clientSocket);
                    // Create and start a new thread to handle the client connection
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                logger.severe("Server error: " + e.getMessage());
            }
        }
    }

    // Nested Client class that implements Runnable to handle client logic
    static class Client implements Runnable {
        @Override
        public void run() {
            try (Socket socket = new Socket("localhost", PORT);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

                String inputLine;
                // Read input from the console and send it to the server
                while ((inputLine = userInput.readLine()) != null) {
                    out.println(inputLine);
                    String receivedMessage = in.readLine();
                    if (receivedMessage == null) {
                        System.out.println("Server closed the connection.");
                        break;
                    }
                    System.out.println("Server: " + receivedMessage);
                    // Exit if the user types "exit"
                    if ("exit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                }
            } catch (IOException e) {
                logger.severe("Client error: " + e.getMessage());
            }
        }
    }

    // Nested ClientHandler class that implements Runnable to handle each client's communication
    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor to initialize clientSocket
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String message;
                // Loop to handle incoming messages from the client
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);
                    out.println("Message received: " + message);
                    // Exit the loop if the client sends "exit"
                    if ("exit".equalsIgnoreCase(message)) {
                        break;
                    }
                }
            } catch (IOException e) {
                logger.severe("ClientHandler error: " + e.getMessage());
            }
        }
    }
}