///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.lab.based.practical.mock.http;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import java.com.sun.net.httpserver.HttpExchange;
//import java.com.sun.net.HttpHandler;
//import java.com.sun.net.HttpServer;
//
//import java.net.InetSocketAddress;
//
//import java.nio.charset.StandardCharsets;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//
///*
// * ----------------
// * Please define a class named 'SimpleHttpServer' to encapsulate the HTTP server functionality.
// * ----------------
//*/
//public class SimpleHttpServer {
//    
//    private static final Logger logger = Logger.getLogger(SimpleHttpServer.class.getName());
//
//    public static void main(String[] args) {
//        logger.info("[MAIN] Starting SimpleHttpServer intialization");
//    
//   
//        
//        try (HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0)) {
//            logger.info("[MAIN] Server instance created successfully");
//
//            
//            server.createContext("/myendpoint", new myHandler());
//            logger.info("[MAIN] Context for /myendpoint created and associated with myHandler");
//
//
//            
//            server.setExecutor(null);
//            logger.info("[MAIN] Server executor set to null");
//            
//
//            server.start();
//            System.out.println("Server started on port 8080");
//            logger.info("[MAIN] Server started on port 8080");
//        
//            
//        } catch (IOException e) {
//            System.out.println("[MAIN] IOException happended: " + e);
//            logger.log(Level.SEVERE, "Server initialization failed, " + e);
//        }
//        
//        logger.info("[MAIN] Server has been shut down...");
//    }
//}
//
//    
//    static class MyHandler implements HttpHandler {
//        private static final LOgger logger = Logger.getLogger(MyHandler.class.getName());
//        
//        @Override 
//        public void handle(HttpExchange exchange) throws IOException {
//            logger.info("[MyHandler] Request received: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());
//            
//            
//            String method = exchange.getRequestMethod();
//            String path = exchange.getRequestURI().getPath();
//            logger.info("Processing request: Method=" + method + ", Path= " + path);
//            
//            
//            
//            
//        }
//
//
//            /*
//             * ----------------
//             * if the endpoint} catch (SocketException e) {
//                logger.log(Level.SEVERE, "[SERVER] SocketException occurred!", e);
//            } catch (SocketTimeoutException e) {
//                logger.log(Level.SEVERE, "[SERVER] SocketTimeoutException occurred!", e);
//            } catch (IllegalBlockingModeException e) {
//                logger.log(Level.SEVERE, "[SERVER] IllegalBlockingModeException occurred!", e);
//            } catch (SecurityException e) {
//                logger.log(Level.SEVERE, "[SERVER] SecurityException occurred!", e);
//            } catch (IOException e) {
//                logger.log(Level.SEVERE, "[SERVER] IOException occurred!", e);
//            } equals path
//                * invoke/call the sendResponse method and pass necessary arguments. 
//                * then use return keyword
//             * ----------------
//            */
//
//
//            /*
//             * ---------------
//             * check if the request method is "GET"
//             * ---------------
//            */
//
//
//            /*
//            * ------------------
//            * otherwise check if the request method is "POST"
//            * ------------------
//            */
//
//                /*
//                 * ------------------
//                 * define a string variable called response and store a message like "This is a POST request to /myendpoint"
//                 * call sendResponse method and provide necessary arguments
//                 * ------------------
//                */
//
//            /*
//            * ----------------------
//            * other wise Handle unsupported methods by calling sendResponse method and pass necessary arguments 
//            * ----------------------
//            */
//
//
//        /*
//         * -------------------------
//         * Please define a private void method called handleGetRequest which accepts an object of the HttpExchange called exchange, 
//           throwing io exception
//         * -------------------------
//        */
//
//
//            /*
//             * -------------
//             * Please define a string variable called response and include this message: "This is a GET request to /myendpoint"
//             * -------------
//            */
//
//
//            /*
//             * -------------------
//             * Please invoke sendResponse method and pass necessary arguments
//             * -------------------
//            */
//
//        /*
//         * -------------------------
//         * Please define a private void method called sendResponse and pass these arguments: 
//           HttpExchange object: exchange, a string variable response, an integer variable statusCode
//         * -------------------------
//        */
//
//
//            /*
//             * -------------
//             * Please get response headers using exchange object and Set the "Content-Type" header to "text/plain".
//             * -------------
//            */
//           
//
//            /*
//             * ------------------
//             * Please send response headers using exchange object and pass response code, 
//               and convert the response to bytes and get the length of that
//             * ------------------
//            */
//        
//
//            /*
//             * --------------------
//             * Please define a try block and inside it create an object of the OutputStream called os 
//               and get the response body using exchange object
//             * Send the response using os object and pass response as its argument by converting it to bytes.
//             * --------------------
//            */
//}