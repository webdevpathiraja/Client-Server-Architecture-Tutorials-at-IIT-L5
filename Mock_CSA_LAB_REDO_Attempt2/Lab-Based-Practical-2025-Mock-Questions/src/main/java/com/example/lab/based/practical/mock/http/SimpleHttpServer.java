/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab.based.practical.mock.http;

/*
 * ----------------
 * Please import necessary libraries
 * ----------------
*/

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
 * ----------------
 * Please define a class named 'SimpleHttpServer' to encapsulate the HTTP server functionality.
 * ----------------
*/

public class SimpleHttpServer {
    // Add logger for the class
    private static final Logger LOGGER = Logger.getLogger(SimpleHttpServer.class.getName());

    /*
     * ----------------
     * Please define the main method, the entry point of the application.
     * This method sets up and starts the HTTP server.
     * ----------------
    */
    
    public static void main(String[] args) {
        LOGGER.info("Starting SimpleHttpServer initialization");

        /*
         * ----------------
         * Please create an HttpServer instance called server that listens on port 8080. Set the backlog to 0
         * Use try-with-resources to ensure the server is closed properly.
         * ----------------
        */
       
        try {
            LOGGER.info("Creating HTTP server instance");
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            /*
             * ----------------
             * Create a context for "/myendpoint" and handle it with MyHandler.
             * ----------------
            */
            
            server.createContext("/myendpoint", new MyHandler());
            LOGGER.info("Context for '/myendpoint' created and associated with MyHandler");

            /*
             * ----------------
             * Set the server's executor to null (no thread pool).
             * ----------------
            */
            
            server.setExecutor(null);
            LOGGER.info("Server executor set to null (using direct handoff)");

            /*
             * ----------------
             * Start the server.
             * print "Server started on port 8080"
             * ----------------
            */
            
            server.start();
            System.out.println("Server started on port 8080");
            LOGGER.info("Server successfully started on port 8080");
            
            LOGGER.info("Server is running. Press Enter to stop the server.");
            System.in.read(); // Keep server running until Enter is pressed
            
            // Manually stop the server when done
            LOGGER.info("Stopping the server");
            server.stop(0);
            LOGGER.info("Server has been stopped");
            
        } catch (IOException e) {
            System.out.println("Exception happened: " + e);
            LOGGER.log(Level.SEVERE, "Server initialization failed", e);
        }
        
        LOGGER.info("Server application has terminated");
    }

    /*
     * ----------------
     * Define a static inner class 'MyHandler' to handle HTTP requests to '/myendpoint'. 
     * This class implements HttpHandler 
     * ----------------
    */
    
    static class MyHandler implements HttpHandler {
        private static final Logger LOGGER = Logger.getLogger(MyHandler.class.getName());

        /*
         * -----------------------
         * Please override the handle method which accepts HttpExchange object called exchange which throws io exception
         * -----------------------
        */
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            LOGGER.info("Request received: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

            /*
             * ----------------
             * please get request method using exchange object and store it insdie a string variable called string
             * please get the request URI and the related path using exchange object and store it in a string variable called path
             * ----------------
            */
            
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            LOGGER.info("Processing request: Method=" + method + ", Path=" + path);

            /*
             * ----------------
             * if the endpoint equals path
                * invoke/call the sendResponse method and pass necessary arguments. 
                * then use return keyword
             * ----------------
            */
           
            if (!path.equals("/myendpoint")) {
                LOGGER.warning("Invalid path requested: " + path);
                sendResponse(exchange, "Not Found", 404);
                return;
            }

            /*
             * ---------------
             * check if the request method is "GET"
             * ---------------
            */
           
            if (method.equalsIgnoreCase("GET")) {
                LOGGER.info("Processing GET request");
                handleGetRequest(exchange);
            }

            /*
            * ------------------
            * otherwise check if the request method is "POST"
            * ------------------
            */
           
            else if (method.equalsIgnoreCase("POST")) {
                LOGGER.info("Processing POST request");

                /*
                 * ------------------
                 * define s atring varibale called response and store a message like "This is a POST request to /myendpoint"
                 * call sendResponse method and provide necessary arguments
                 * ------------------
                */
                
                String response = "This is a POST request to /myendpoint. Request body is ignored.";
                sendResponse(exchange, response, 200);
            }

            /*
            * ----------------------
            * other wise Handle unsupported methods by calling sendResponse method and pass necessary arguments 
            * ----------------------
            */
            
            else {
                LOGGER.warning("Unsupported method requested: " + method);
                sendResponse(exchange, "Method Not Allowed", 405);
            }
        }

        /*
         * -------------------------
         * Please define a private void method called handleGetRequest which accepts an object of the HttpExhcnage called exchange, 
           throwing io exception
         * -------------------------
        */
        
        private void handleGetRequest(HttpExchange exchange) throws IOException {
            LOGGER.info("Handling GET request");

            /*
             * -------------
             * Please define a string variable called response and include this message: "This is a GET request to /myendpoint"
             * -------------
            */
            
            String response = "This is a GET request to /myendpoint";

            /*
             * -------------------
             * Please invoke sendResponse methos and pass necessary arguments
             * -------------------
            */
            
            LOGGER.info("Sending response for GET request");
            sendResponse(exchange, response, 200);
        }

        /*
         * -------------------------
         * Please define a private void method called sendResponse and pass these arguments: 
           HttpExhange object: exchange, a string varibale reponse, an integer varibale statusCode
         * -------------------------
        */
       
        private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
            LOGGER.info("Preparing response with status code: " + statusCode);

            /*
             * -------------
             * Please get response headers using exchange object and Set the "Content-Type" header to "text/plain".
             * -------------
            */
            
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            LOGGER.info("Content-Type header set to text/plain");

             /*
             * ------------------
             * Please send response headers using exchange object and pass response code, 
               and convert the reponse to bytes and get the length of that
             * ------------------
            */
            
            exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
            LOGGER.info("Response headers sent with status code: " + statusCode);

            /*
             * --------------------
             * Please define a try block and inside it create an object of the OutputStream called os 
               and get the response body using exchange object
             * Send the response using os object and pass reponse as its argument by converting it to bytes.
             * --------------------
            */
            
            try (OutputStream os = exchange.getResponseBody()) {
                LOGGER.info("Writing response body");
                os.write(response.getBytes(StandardCharsets.UTF_8));
                LOGGER.info("Response successfully sent");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error writing response body", e);
                throw e;
            }
        }
    }
}
