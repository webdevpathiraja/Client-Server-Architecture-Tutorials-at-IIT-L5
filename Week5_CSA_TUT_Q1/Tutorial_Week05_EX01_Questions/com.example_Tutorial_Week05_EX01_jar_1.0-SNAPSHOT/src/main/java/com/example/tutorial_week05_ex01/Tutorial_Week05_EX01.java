/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.example.tutorial_week05_ex01;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject; // Requires a JSON library.  See note below.
import org.json.JSONArray;

public class Tutorial_Week05_EX01 {


    /*
     * Please declare a static List of Maps called 'movies'. This will hold the
     * movie data. Each Map represents a movie, with keys as attribute names
     * (e.g., "id", "title", "genre") and values as the corresponding values.
     * This is a simple in-memory store; for real applications, use a database.
     */
    private static List<Map<String, Object>> movies = new ArrayList<>();

    /*
     * Please define the main method, the entry point of the application.
     * This method sets up and starts the HTTP server.
     */
    public static void main(String[] args) throws IOException{
        
    
        /*
         * Please add some initial movie data to the 'movies' list.
         * Call the addMovie() method to add at least two movies.
         */
        addMovie(1, "Movie A", "Action");
        addMovie(2, "Movie B", "Comedy");

        /*
         * Please create an HTTP server that listens on port 8080.
         * Use HttpServer.create() with an InetSocketAddress and a backlog of 0.
         */
        // creating HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        
        

        /*
         * Please create context paths (endpoints) for the API.
         * Use server.createContext().
         *
         * Create a context path "/movies" and associate it with a new
         * MoviesHandler instance. This handles requests for all movies.
         *
         * Create a context path "/movies/" (with a trailing slash) and
         * associate it with a new MovieHandler instance. This handles
         * requests for a specific movie by ID. The trailing slash is
         * important to distinguish between the two endpoints.
         */
        // setting up context paths
        server.createContext("/movies", new MoviesHandler());
        server.createContext("/movies/", new MoviesHandler());
        
        
        

        /*
         * Please set the server's executor to null to use the default executor.
         * For larger applications, consider using a thread pool.
         */
        // using default executor
        server.setExecutor(null);
        
        

        /*
         * Please start the server and print "server started on port 8080"
         */
        // starting server
        System.out.println("server started on port 8080");
        server.start();
        
        
        
    }

    /*
     * Please create a helper method addMovie(id, title, genre) that adds a
     * new movie to the 'movies' list. Create a HashMap for the movie data
     * and add it to the list.
     */
    
    // method to add movies
    private static void addMovie(int id, String title, String genre) {
        Map<String, Object> movie = new HashMap<>();
        movie.put("id", id);
        movie.put("title", title);
        movie.put("genre", genre);
        movies.add(movie);
    }
    
    
    /*
     * Please create a class MoviesHandler that implements HttpHandler.
     * This class handles requests to the /movies endpoint.
     */
    static class MoviesHandler implements HttpHandler {
        /*
         * Please implement the handle(exchange) method.
         * Check the request method with an argument as an object of HttpExchange that throws IOException:
         * - If GET, call handleGetMovies(exchange).
         * - If POST, call handlePostMovie(exchange).
         * - Otherwise, send a 405 Method Not Allowed response.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(requestMethod)) {
                handleGetMovies(exchange);
            } else if ("POST".equalsIgnoreCase(requestMethod)) {
                handlePostMovie(exchange);
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        } 
    }

    /*
     * Please create a class MovieHandler that implements HttpHandler.
     * This class handles requests to the /movies/{id} endpoint.
     */
    static class MovieHandler implements HttpHandler {
        /*
         * Please implement the handle(exchange) method.
         * If the request method is GET, call handleGetMovieById(exchange).
         * Otherwise, send a 405 Method Not Allowed response.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                handleGetMovieById(exchange);
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        }
    }

    /*
     * Please create a method handleGetMovies(exchange) to handle GET requests
     * to /movies. Convert the 'movies' list to a JSONArray and send it as
     * the response.
     */
    private static void handleGetMovies(HttpExchange exchange) throws IOException {
        JSONArray jsonMovies = new JSONArray(movies);
        sendResponse(exchange, 200, jsonMovies.toString());
    }

    /*
     * Please create a method handleGetMovieById(exchange) to handle GET
     * requests to /movies/{id}. Extract the movie ID from the path, find
     * the movie in the 'movies' list, convert it to a JSONObject, and send
     * it as the response. Handle invalid IDs (non-numeric) and "movie not
     * found" cases with appropriate 400 and 404 responses.
     */
    private static void handleGetMovieById(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String idStr = path.substring(path.lastIndexOf('/') + 1);
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Movie ID"); // 400 Bad Request
            return;
        }

        for (Map<String, Object> movie: movies) {
            if ((int) movie.get("id") == id) {
                JSONObject jsonMovie = new JSONObject(movie);
                sendResponse(exchange, 200, jsonMovie.toString());
                return;
            }
        }

        sendResponse(exchange, 404, "Movie Not Found"); // 404 Not Found
    }

    /*
     * Please create a method handlePostMovie(exchange) to handle POST requests
     * to /movies. Read the request body, parse it as a JSONObject, add the
     * new movie to the 'movies' list, and send a 201 Created response. Handle
     * invalid JSON format with a 400 Bad Request response. Validate the
     * incoming JSON and ensure it has "id", "title", and "genre" fields; if
     * not, return a 400 response.
     */
    private static void handlePostMovie(HttpExchange exchange) throws IOException {
        try {
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            JSONObject jsonMovie = new JSONObject(requestBody);
            if (!jsonMovie.has("id") ||!jsonMovie.has("title") ||!jsonMovie.has("genre")) {
                sendResponse(exchange, 400, "Invalid movie format. Must have id, title, and genre."); // 400 Bad Request
                return;
            }
            movies.add(jsonMovie.toMap()); // Add to our in-memory list
            sendResponse(exchange, 201, jsonMovie.toString()); // 201 Created
        } catch (Exception e) {
            sendResponse(exchange, 400, "Invalid JSON format: " + e.getMessage()); // 400 Bad Request
        }
    }

    /*
     * Please create a helper method sendResponse(exchange, statusCode, response)
     * to send HTTP responses. Set the Content-Type header to "application/json"
     * and send the given status code and response body.
     */
    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
  
}
