package org.ordersMicroservice.exception;

public class EmptyOrderDetailException extends RuntimeException {

    public EmptyOrderDetailException(String message) {

        super(message);
    }
}
