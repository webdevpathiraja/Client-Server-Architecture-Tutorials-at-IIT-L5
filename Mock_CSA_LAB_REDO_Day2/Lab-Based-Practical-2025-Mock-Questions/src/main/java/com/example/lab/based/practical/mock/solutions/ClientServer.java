/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab.based.practical.mock.solutions;


/*
 * ----------------
 * Please import libraries
 * ----------------
*/
/*
 * ----------------
 * Please import libraries
 * ----------------
 */
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
 * ----------------
 * Please define a class named 'ClientServer' that will contain both the server and client logic.
 * This demonstrates a simple client-server interaction within a single class.
 * ----------------
 */
public class ClientServer {

    // Fix: Corrected Logger name (LOgger -> Logger)
    private static final Logger logger = Logger.getLogger(ClientServer.class.getName());

    // Server Port Number
    private static final Integer PORT = 12345;

    // Main method
    public static void main(String[] args) {

        logger.info("[MAIN] Server and Client Threads starting");

        // Start Server Thread
        Thread serverThread = new Thread(new Server());
        serverThread.start();
        logger.info("[MAIN] Server Thread started");

        // Start Client Thread
        Thread clientThread = new Thread(new Client());
        clientThread.start();
        logger.info("[MAIN] Client Thread started");
    }

    
    
    static class Server implements Runnable {

        @Override
        public void run() {
            logger.info("[SERVER] Starting server...");

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                logger.info("[SERVER] Server started on port " + PORT);

                while (true) {
                    logger.info("[SERVER] Waiting for a client connection...");
                    Socket clientSocket = serverSocket.accept();
                    logger.info("[SERVER] Client connected: " + clientSocket);

                    // Start a new thread for each client
                    Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                    clientHandler.start();
                    logger.info("[SERVER] ClientHandler thread started for " + clientSocket);
                }

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
            }
        }
    }
    
    
    
    
        
    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            logger.info("[CLIENT-HANDLER] Initializing handler for new client socket: " + clientSocket);
            this.clientSocket = clientSocket;
        }

        @Override 
        public void run() {
            logger.info("[CLIENT-HANDLER] Started handling client: " + clientSocket);

            // Establish communication channels
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                logger.info("[CLIENT-HANDLER] Input and output streams established successfully for: " + clientSocket);

                String inputLine;

                // Start message-processing loop
                while ((inputLine = in.readLine()) != null) {
                    logger.info("[CLIENT-HANDLER] Received message from client: " + inputLine);
                    System.out.println("[SERVER] server received: " + inputLine);

                    // Check for exit condition
                    if ("exit".equalsIgnoreCase(inputLine)) {
                        logger.info("[CLIENT-HANDLER] Exit command received from client: " + clientSocket);
                        out.println("Bye");
                        logger.info("[CLIENT-HANDLER] Sent 'Bye' response to client: " + clientSocket);
                        break;
                    }

                    // Echo message back
                    logger.info("[CLIENT-HANDLER] Preparing echo response for: " + inputLine);
                    out.println("Echo: " + inputLine);
                    logger.info("[CLIENT-HANDLER] Sent echo response to client: " + clientSocket);
                }

                logger.info("[CLIENT-HANDLER] Client message loop has ended for: " + clientSocket);

            } catch (IOException e) {
                logger.severe("[CLIENT-HANDLER] IOException occurred while handling client: " + clientSocket + " | Error: " + e.getMessage());

            } finally {
                logger.info("[CLIENT-HANDLER] Attempting to close client socket: " + clientSocket);
                try {
                    clientSocket.close();
                    logger.info("[CLIENT-HANDLER] Client socket successfully closed: " + clientSocket);
                } catch (IOException e) {
                    logger.severe("[CLIENT-HANDLER] Error while closing client socket: " + clientSocket + " | Error: " + e.getMessage());
                }
            }

            logger.info("[CLIENT-HANDLER] Finished handling client: " + clientSocket);
        }
    }


    static class Client implements Runnable {

        private static final Logger logger = Logger.getLogger(Client.class.getName());
        private static final Integer PORT = 12345;

        @Override
        public void run() {

            try {
                logger.info("[CLIENT] Starting client... preparing to connect.");

                try (

                    Socket socket = new Socket("localhost", PORT);


                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                    BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    logger.info("[CLIENT] Successfully connected to server at localhost:" + PORT);
                    System.out.println("[CLIENT] Connected to server at localhost:" + PORT);


                    String userInput;
                    while (true) {
                        logger.info("[CLIENT] Waiting for user input...");
                        userInput = consoleInput.readLine();
                        logger.info("[CLIENT] User entered: " + userInput);


                        logger.info("[CLIENT] Sending message to server...");
                        out.println(userInput);
                        logger.info("[CLIENT] Message sent to server: " + userInput);


                        logger.info("[CLIENT] Waiting for server response...");
                        String receivedMessage = in.readLine();
                        if (receivedMessage == null) {
                            logger.warning("[CLIENT] Server closed the connection.");
                            System.out.println("[CLIENT] Server closed the connection.");
                            break;
                        }
                        logger.info("[CLIENT] Received from server: " + receivedMessage);


                        System.out.println("[CLIENT] Server says: " + receivedMessage);


                        if ("exit".equalsIgnoreCase(userInput)) {
                            logger.info("[CLIENT] Exit command received. Terminating connection...");
                            break;
                        }
                    }

                } catch (IOException e) {
                    logger.log(Level.SEVERE, "[CLIENT] Communication error: ", e);
                }

                logger.info("[CLIENT] Client shutting down.");

            } catch (Exception e) {
                logger.log(Level.SEVERE, "[CLIENT] Unexpected error", e);
            }
        }
    }
}