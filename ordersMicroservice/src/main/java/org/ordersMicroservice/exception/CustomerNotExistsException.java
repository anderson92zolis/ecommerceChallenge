package org.ordersMicroservice.exception;

public class CustomerNotExistsException extends RuntimeException {

    public CustomerNotExistsException(String message) {

        super(message);
    }
}
