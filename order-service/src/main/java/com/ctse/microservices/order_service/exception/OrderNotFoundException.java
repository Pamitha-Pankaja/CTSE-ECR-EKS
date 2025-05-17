package com.ctse.microservices.order_service.exception;

/**
 * Custom exception thrown when an order with a specific ID is not found in the database.
 *
 * This exception is handled by the GlobalExceptionHandler to return a 404 Not Found response.
 */
public class OrderNotFoundException extends RuntimeException {

    /**
     * Constructs a new OrderNotFoundException with a custom error message.
     *
     * @param id The ID of the order that could not be found.
     */
    public OrderNotFoundException(String id) {
        super("No order found with ID: " + id); // Sets the exception message
    }
}
