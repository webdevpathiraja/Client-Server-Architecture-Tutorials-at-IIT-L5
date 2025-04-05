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
    private static final int PORT = 12345;

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
        
        static class ClientHandler implements Runnable {
            private final Socket clientSocket;
            
            public ClientHandler(Socket clientSocket) {
                this.clientSocket = clientSocket;
            }
            
            @Override 
            public void run() {
                logger.info("[CLENT-HANDLER] starting handling client " + clientSocket);
                
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
    
    
    
        
        
    
    
    
    
}

        
        
        



            
                
                



    /*
     * ----------------
     * Please define a nested class named 'Client' that implements the Runnable interface.
     * This class encapsulates the client's functionality and allows it to run in a separate thread.
     * ----------------
    */
    


        /*
         * ----------------
         * Please override the run method from the Runnable interface.
         * This method contains the main logic for the client.
         * ----------------
        */
        



            /*
             * ----------------
             * Use try-with-resources to ensure resources are closed automatically.
             * ----------------
            */
          

                    /*
                     * --------------------
                     * Please create a Socket object, establishing a connection to the server at the specified host 
                       ("localhost") and port (PORT).
                     * This initiates the connection to the server's ServerSocket.
                     * --------------------
                    */
                    


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