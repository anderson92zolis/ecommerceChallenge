package org.ecommerce.products.exception;

import org.bouncycastle.cert.ocsp.RespData;
import org.ecommerce.products.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFound ex){
        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
