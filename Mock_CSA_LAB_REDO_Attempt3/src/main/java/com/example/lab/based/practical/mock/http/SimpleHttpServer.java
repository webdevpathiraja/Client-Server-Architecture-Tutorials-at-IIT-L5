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
import java.io.InputStream;
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
    }
    
}

       



            /*
             * ----------------
             * Create a context for "/myendpoint" and handle it with MyHandler.
             * ----------------
            */
            



            /*
             * ----------------
             * Set the server's executor to null (no thread pool).
             * ----------------
            */
            



            /*
             * ----------------
             * Start the server.
             * print "Server started on port 8080"
             * ----------------
            */
            




    /*
     * ----------------
     * Define a static inner class 'MyHandler' to handle HTTP requests to '/myendpoint'. 
     * This class implements HttpHandler 
     * ----------------
    */
    


        /*
         * -----------------------
         * Please override the handle method which accepts HttpExchange object called exchange which throws io exception
         * -----------------------
        */
        



            /*
             * ----------------
             * please get request method using exchange object and store it insdie a string variable called string
             * please get the request URI and the related path using exchange object and store it in a string variable called path
             * ----------------
            */
            




            /*
             * ----------------
             * if the endpoint equals path
                * invoke/call the sendResponse method and pass necessary arguments. 
                * then use return keyword
             * ----------------
            */
           




            /*
             * ---------------
             * check if the request method is "GET"
             * ---------------
            */
           


            /*
            * ------------------
            * otherwise check if the request method is "POST"
            * ------------------
            */
           



                /*
                 * ------------------
                 * define s atring varibale called response and store a message like "This is a POST request to /myendpoint"
                 * call sendResponse method and provide necessary arguments
                 * ------------------
                */
                



            /*
            * ----------------------
            * other wise Handle unsupported methods by calling sendResponse method and pass necessary arguments 
            * ----------------------
            */
            



        /*
         * -------------------------
         * Please define a private void method called handleGetRequest which accepts an object of the HttpExhcnage called exchange, 
           throwing io exception
         * -------------------------
        */
        


            /*
             * -------------
             * Please define a string variable called response and include this message: "This is a GET request to /myendpoint"
             * -------------
            */
            


            /*
             * -------------------
             * Please invoke sendResponse methos and pass necessary arguments
             * -------------------
            */
            


        /*
         * -------------------------
         * Please define a private void method called sendResponse and pass these arguments: 
           HttpExhange object: exchange, a string varibale reponse, an integer varibale statusCode
         * -------------------------
        */
       


            /*
             * -------------
             * Please get response headers using exchange object and Set the "Content-Type" header to "text/plain".
             * -------------
            */
            


             /*
             * ------------------
             * Please send response headers using exchange object and pass response code, 
               and convert the reponse to bytes and get the length of that
             * ------------------
            */
            


            /*
             * --------------------
             * Please define a try block and inside it create an object of the OutputStream called os 
               and get the response body using exchange object
             * Send the response using os object and pass reponse as its argument by converting it to bytes.
             * --------------------
            */
     

