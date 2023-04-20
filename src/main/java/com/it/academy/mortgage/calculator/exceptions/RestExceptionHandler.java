package com.it.academy.mortgage.calculator.exceptions;

import com.it.academy.mortgage.calculator.errors.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorMessage> customerNotFoundException(CustomerNotFoundException ex, WebRequest request){
        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CalculatorException.class)
    static public ResponseEntity<String> handleEuriborException(CalculatorException calculatorException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(calculatorException.getMessage());
    }

}
