package com.it.academy.mortgage.calculator.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String id ){
        super("No customer found with id: " + id);
    }
}
