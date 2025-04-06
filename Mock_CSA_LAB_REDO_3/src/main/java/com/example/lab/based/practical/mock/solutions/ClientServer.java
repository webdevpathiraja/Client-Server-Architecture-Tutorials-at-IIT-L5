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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.SeverSocket;
import java.net.SocketTimeoutException;
import java.net.SocketException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.logging.Logger;
import java.util.logging.Level;



/*
 * ----------------
 * Please define a class named 'ClientServer' that will contain both the server and client logic.
 * This demonstrates a simple client-server interaction within a single class.
 * ----------------
*/
public static class ClientServer {
   
    private static final Logger logger = Logger.getLogger(ClientServer.class.getName());


    private static final Integer PORT = 12345;
    

    public static void main(String[] args) {
        logger.info("[MAIN] Starting server and client threads...");
        
    
        Thread serverThread = new Thread(new Server());
        serverThread.start();
        logger.info("[MAIN] serverThread started!");
      
        Thread clientThread = new Thread(new Client());
        clientThread.start();
        logger.info("[MAIN] clientThread started!");
        
    }

    static class Server implements Runnable{
        
        @Override
        public void run() {
            logger.info("[SERVER] Starting server...");
            
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                logger.info("[SERVER] Starting server on port: " + PORT);
        
                while(true) {
                    
                    logger.info("[SERVER] Waiting for a client connection...");
                    Socket clientSocket = serverSocket.accept();
                    logger.info("[SERVER] Client connected to the server: " + clientSocket);
                    System.out.println("[SERVER] Client connected to the server" + clientSocket);
                    
                    Thread clientHandler =  new Thread(new ClientHandler(clientSocket));
                    clientHandler.start();
                    logger.info("[SERVER] clientHandler thread started for: " + clientSocket);
                   
                }   
                
            }  catch (SocketException e) {
                logger.log(Level.SEVERE, "[SERVER] SocketException occued!", e);
            }  catch (IllegalBlockingModeException e) {
                logger.log(Level.SEVERE, "[SERVER]IllegealBlockingModeException occued!", e);
            }  catch (SecurityException e) {
                logger.log(Level.SEVERE, "[SERVER]SecurityException occued!", e);
            }  catch (SocketTimeoutException e) {
                logger.log(Level.SEVERE, "[SERVER] SocketTimeoutException occued!", e);
            }  catch (IOException e) {
                logger.log(Level.SEVERE, "[SERVER] IOException occued!", e);
            }
        }
    }




        /*
         * ----------------
         * Please define a nested class named 'ClientHandler' that implements the Runnable interface.
         * This class handles the interaction with a single connected client.
         * ----------------
        */
    static class ClientHandler implements Runnable{
        private final Socket clientSocket;
        
        public ClientHandler(Socket clientSocket) {
            logger.info("[CLIENT-HANDLER] Initializing new handler for: " + clientSocket);
            this.clientSocket = clientSocket;
        }
        
        @Override
        public void run() {
            logger.info("[CLIENT-HANDLER] Startd handling client: " + clientSocket);
            
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                logger.info("[CLIENT-HANDLER] Input and output streams established successfully for: " + clientSocket);
                
                
                String inputLine;
                
                while((inputLine = in.readLine()) != null) {
                    logger.info("[CLIENT-HANDLER] Received message from the client: " + inputLine);
                    System.out.println("Recieved message from client:  " + inputLine);
                    
                    if ("exit".equalsIgnoreCase(inputLine)) {
                        logger.info("[CLIENT-HANDLER] User request 'exit'...");
                        System.out.println("Bye!");
                        logger.info("[CLIENT-HANDLER] Sent bye response to the client: " + clientSocket);  
                        break;
                    }
                    
                    logger.info("[CLIENT-HANDLER] Preparing echo response for: " + inputLine);
                    out.println("echo: " + inputLine);
                    logger.info("[CLIENT-HANDLER] Sending echo response: " + inputLine + " to: " + clientSocket);   
                }
                
                logger.info("[CLIENT-HANDLER] Client message loop ended for: " + clientSocket); 
            }
            
            catch (IOException e) {
                logger.log(Level.SEVERE, "IO Exception occured!", e);
            } finally {
                try {
                    clientSocket.close();
                    logger.info("[CLIENT-HANDLER] Client socket closed successfully!!...");
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "IOExpection occured while closing the socket...", e);
                    
                }
            }
        }
    }
                
                

    
    
                    


                    /*
                     * ----------------
                     * Please create a PrintWriter to send formatted text data to the server through the socket's output stream.
                     * PrintWriter simplifies sending text-based data.
                     * 'true' for auto-flush: ensures data is sent immediately without buffering delays.
                     * Wrap socket.getOutputStream() to provide character-based output.
                     * ----------------
                    */
                    


                    /*
                     * ----------------
                     * Please Create a BufferedReader to read text data from the server's responses through the socket's input stream.
                     * BufferedReader provides efficient reading of characters, lines, and arrays by buffering the input.
                     * Wrap socket.getInputStream() with InputStreamReader to convert bytes to characters.
                     * ----------------
                    */
                    


                    /*
                     * -------------------
                     * Please create a BufferedReader to read text input from the console (System.in).
                     * This allows the client to read user input.
                     * Wrap System.in (which is an InputStream) with an InputStreamReader to handle character encoding.
                     * -------------------
                    */
                    


    

                /*
                 * ----------------
                 * Please read input from the console  in a loop.
                 * ----------------
                */
                


                    /*
                     * ----------------
                     * Please send the user's input to the server using the PrintWriter.
                     * ----------------
                    */
                    



                    /*
                     * ----------------
                     * Please read the server's response using the BufferedReader and store it inside a string variable called receivedMessage .
                     * check if the receivededMessage is null
                     * Then print "Server closed the connection."
                     * exit the loop
                     * ----------------
                    */
                    


                    /*
                     * ---------------
                     * Print Server Response 
                     * ---------------
                    */
                    

                    /*
                     * ----------------
                     * Please check if the user typed "exit". If so, exit the loop.
                     * ----------------
                    */
                    

}