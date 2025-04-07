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
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;


public class SimpleHttpServer {
    
    private static final Logger logger = Logger.getLogger(SimpleHttpServer.class.getName());
    
    public static void main(String[] args) {

        logger.info("[MAIN] Starting SimpleHttpServer initialization...");

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            logger.info("[MAIN] Server created on port 8080");

            server.createContext("/myendpoint", new MyHandler());
            logger.info("[MAIN] Created server context /myendpoint and associated with MyHandler");

            server.setExecutor(null);
            logger.info("[MAIN] Set server execute to null");

            server.start();
            logger.info("[MAIN] Server started and running on port 8080...");
            logger.info("[MAIN] Press enter to close the server manually");

            System.in.read();

            logger.info("[MAIN] Requested to close the server");
            logger.info("[MAIN] Closing server...");
            server.stop(0);
            logger.info("[MAIN] Server closed!!");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occured!", e);
            System.out.println("IOException occured, Server initialization failed: " + e);

        }

        logger.info("Server application has terminated");
    }
    
    
    
    
    
    static class MyHandler implements HttpHandler{
        private static final Logger logger = Logger.getLogger(MyHandler.class.getName());

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                
                logger.info("Request received: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

                String method = exchange.getRequestMethod();
                String path = exchange.getRequestURI().getPath();
                logger.info("[MyHandler] Processing request: Method = " + method + ", Path = " + path);

                if(!path.equals("/myendpoint")) {
                    logger.warning("[MeHandler] Invalid path requeted: " + path);
                    sendResponse(exchange, "Not Found", 404);
                    return;
                }
                
                if(method.equalsIgnoreCase("GET")) {
                    logger.info("[MyHandler] Processing Get request");
                    handleGetRequest(exchange);
                }
                
                else if(method.equalsIgnoreCase("POST")) {
                    logger.info("[MyHandler] Processing POST request");
                    String response = "This is a POST request to /myendpoint, Request body is ignored";
                    sendResponse(exchange, response, 200);
                }
                
                else {
                    logger.warning("[MyHandler] Unsupported method requested: " + method);
                    sendResponse(exchange, "Method not allowed", 405);
                }

            }
            
            private void handleGetRequest (HttpExchange exchange) throws IOException {
                logger.info("[MyHandler] Handling GET request");
                
                String response = "This is a get request to /myendpoint";
                
                logger.info("[MyHandler] Sending response for GET request");
                sendResponse(exchange, response, 200);   
            }
            
            private void sendResponse(HttpExchange exchange, String response, Integer statusCode)throws IOException {
                logger.info("[MyHandler] Preparing response with status code");
                
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                logger.info("[MyHandler] Content-Type header set to text/plain");
                
                
                exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
                logger.info("[MyHandler] Response headers sent with status code");
                
                try (
                    OutputStream os = exchange.getResponseBody()) {
                        
                    logger.info("[MyHandler] writing response body");
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                    logger.info("[MyHandler] Response sent successfully");
                    
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error writing response body", e);
                    throw e;

                }
            }
    }
    
}

     

