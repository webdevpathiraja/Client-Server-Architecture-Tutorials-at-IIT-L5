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





/*
 * ----------------
 * Please define a class named 'SimpleHttpServer' to encapsulate the HTTP server functionality.
 * ----------------
*/





    /*
     * ----------------
     * Please define the main method, the entry point of the application.
     * This method sets up and starts the HTTP server.
     * ----------------
    */
    



        /*
         * ----------------
         * Please create an HttpServer instance called server that listens on port 8080. Set the backlog to 0
         * Use try-with-resources to ensure the server is closed properly.
         * ----------------
        */
       



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
     

