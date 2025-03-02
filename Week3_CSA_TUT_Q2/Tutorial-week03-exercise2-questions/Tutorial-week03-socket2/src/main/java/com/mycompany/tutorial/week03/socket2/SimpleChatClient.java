/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket2;


/* **Import necessary packages */



/* **Define a public class named SimpleChatClient. This class will contain all the logic for our chat client program.** */


    /* **Define the main method. This is the entry point for any Java application.** */
    

        /* **Try to establish a connection to the server and handle communication with the server. 
        If an IOException occurs, it will be caught and handled in the catch block.** */
       

            /* **Create a new Socket object to connect to the server. 
            The Socket constructor takes two parameters: the server's IP address (or hostname) and the port number.** */


            /* **Print a message to the console indicating that the client has connected to the server.** */

            /* **Get the input stream of the socket. This stream is used to receive data from the server.** */
=

            /* **Get the output stream of the socket. This stream is used to send data to the server.** */


            /* **Create a Scanner object to read input from the user. The Scanner constructor takes an input stream as a parameter. 
            Here, we pass System.in to read input from the console.** */


            /* **Enter an infinite loop where the client will continuously send messages to the server and receive responses.** */


                /* **Prompt the user to enter a message.** */


                /* **Read a line of input from the user.** */


                /* **Send the user's message to the server. The write method of the OutputStream class is used here. 
                It takes a byte array as a parameter, so we call the getBytes method of the String class to convert the message to bytes.** */


                /* **Create a byte array to store the server's response. you may name it "buffer"** */


                /* **Read the server's response into the buffer. The read method of the InputStream class is used here. 
                It reads data from the input stream into the buffer and returns the number of bytes read.** */


                /* **Convert the server's response to a string. The String constructor is used here. 
                It takes three parameters: the byte array containing the data, the offset to start at (0 in this case), and the number of bytes to convert.** */


                /* **Print the server's response to the console.** */
            

        /* **Catch any IOException that may occur and print the stack trace. 
            An IOException is thrown when an input-output operation is failed or interrupted.** */
