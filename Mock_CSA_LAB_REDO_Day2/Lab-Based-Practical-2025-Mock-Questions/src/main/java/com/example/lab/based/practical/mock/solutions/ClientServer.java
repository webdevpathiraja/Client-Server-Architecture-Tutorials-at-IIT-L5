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

    /*
     * ----------------
     * Please define a nested class named 'Server' that implements the Runnable interface.
     * This class encapsulates the server's functionality and allows it to run in a separate thread.
     * ----------------
     */
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
}

        
        
        



            /*
             * ----------------
             * Please define a constructor for the ClientHandler class.
             * This constructor takes the client's socket as a parameter.
             * ----------------
            */
           



            /*
             * ----------------
             * Please override the run method of the Runnable interface.
             * This method contains the logic for communicating with the client.
             * ----------------
            */
            



                /*
                 * ----------------
                 * Use try-with-resources to ensure resources are closed automatically.
                 * Handle potential IOExceptions.
                 * ----------------
                */
                


                        /*
                         * ----------------
                         * Please create a BufferedReader to read text data from the client socket's input stream.
                         * BufferedReader buffers the input for efficient reading of characters, lines, and arrays.
                         * Wrap the getInputStream() (which provides raw byte stream) with an InputStreamReader
                         * to convert the byte stream into a character stream.
                         * ----------------
                        */
                        


                        /*
                         * ----------------
                         * Please create a PrintWriter to send formatted text data to the client socket's output stream.
                         * PrintWriter provides convenient methods for writing various data types (strings, numbers, etc.) as text.
                         * The 'true' argument enables auto-flushing, ensuring that data is sent immediately after each print/println call,
                         * preventing buffering-related delays.  Wrap getOutputStream() (which provides raw byte stream).
                         * ----------------
                        */
                       


                    /*
                     * ----------------
                     * Please read messages from the client in a loop and store it inside a string varibale called inputLine.
                     * Continue reading until the client sends "exit" or the connection is closed.
                     * print the inputLine plus a message like "Server received: "
                     * ----------------
                    */
                    


                        /*
                         * --------------
                         * Please check if the client has sent the "exit" command.
                         * --------------
                        */
                        


                            /*
                             * --------------
                             * If "exit" is received, send a "Bye" message to the client and break out of the loop.
                             * The println() method of PrintWriter writes a string followed by a newline character to the output stream.
                             * Then break the operation
                             * otherwise
                             * --------------
                            */
                            



                            /*
                             * --------------
                             * send the inputLine plus "Echo: "
                             *  
                             * --------------
                            */
                           



                
                /*
                 * --------------
                 * create a try block and close the clientSocket and catch io exception                
                 * --------------
                */
                
                



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