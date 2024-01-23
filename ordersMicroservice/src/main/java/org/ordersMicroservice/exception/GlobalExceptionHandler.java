package org.ordersMicroservice.exception;

import org.ordersMicroservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyOrderDetailException.class)
    public ResponseEntity<ErrorDto> handleEmptyOrderDetailException(EmptyOrderDetailException ex){

        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomerNotExistsException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoudExistsException (CustomerNotExistsException ex) {

        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

}
