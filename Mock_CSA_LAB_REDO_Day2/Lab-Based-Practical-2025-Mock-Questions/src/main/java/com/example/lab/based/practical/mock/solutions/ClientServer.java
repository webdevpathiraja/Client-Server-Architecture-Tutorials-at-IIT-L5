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
    private static final int PORT = 1235;

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
            this.clientSocket = clientSocket;
        }

        @Override 
        public void run() {
            logger.info("[CLIENT-HANDLER] starting handling client " + clientSocket);

            try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                   PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                logger.info("[CLIENT-HANDLER] Established input and output streams for " + clientSocket);

                String inputLine;

                while((inputLine = in.readLine()) != null) {
                    logger.info("[CLIENT-HANDLER] Recived message from client: " + inputLine);
                    System.out.println("[SERVER] server recieved: " + inputLine);


                    if ("exit".equalsIgnoreCase(inputLine)){
                        logger.info("[CLIENT-HANDLER] Client request exit. Closing for: " + clientSocket);
                        out.println("Bye");
                        break;        
                    }

                    out.println("Echo: " + inputLine);
                    logger.info("[CLIENT-HANDLER] sent echo response to the client: " + clientSocket);             
                }



            } catch (IOException e) {
                logger.severe("[CLIENT-HANDLER] Error handling client: " + clientSocket + " |Error: " + e.getMessage());

            } finally {
                try {
                    clientSocket.close();
                    logger.info("[CLIENT-HANDLER] Connection closed for client: " + clientSocket);

                } catch (IOException e) {
                    logger.severe("[CLIENT-HANDLER] Erorr closing client connection: " + clientSocket);
                }
            }

            logger.info("[CLIENT-HANDLER] stopped handling client: " + clientSocket);
        }
    }

    static class Client implements Runnable {

        private static final Logger logger = Logger.getLogger(Client.class.getName());

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