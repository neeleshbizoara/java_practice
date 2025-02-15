package com.example.productdervicefeb25.controlleradvices;

import com.example.productdervicefeb25.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handelProductNotFoundException(ProductNotFoundException exception){
//        return new ResponseEntity<>("Product with the given id doesn't exost.", HttpStatus.BAD_GATEWAY);
        System.out.println("Controller Advice Exception called.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public void handelArrayIndexedOutOfBoundsException(){

    }

    @ExceptionHandler(Exception.class)
    public void handelException(){

    }
}
