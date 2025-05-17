package com.ctse.microservices.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler handles all exceptions thrown from anywhere in the application
 * and returns a proper JSON response with error details and appropriate HTTP status code.
 */
@RestControllerAdvice  // Marks this class as a global exception handler for REST controllers
public class GlobalExceptionHandler {

    /**
     * Handles any uncaught general exceptions in the application.
     * This prevents server errors from crashing the app and returns a generic message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex.getMessage());
    }

    /**
     * Handles IllegalArgumentExceptions which may occur due to invalid inputs or bad arguments.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid input", ex.getMessage());
    }

    /**
     * Handles custom OrderNotFoundExceptions thrown when an order is not found in the database.
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(OrderNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Order not found", ex.getMessage());
    }

    /**
     * Utility method to build a standard JSON error response structure.
     *
     * @param status  The HTTP status code to return
     * @param error   A short description of the error
     * @param message The detailed exception message
     * @return A ResponseEntity with a structured error body and status
     */
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());      // Current server time
        body.put("status", status.value());              // HTTP status code (e.g., 404, 500)
        body.put("error", error);                        // Short error name (e.g., "Not Found")
        body.put("message", message);                    // Detailed error message
        return new ResponseEntity<>(body, status);       // Return response entity
    }
}
