package org.ecommerce.products.exception;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound (String message){
        super(message);
    }

}
