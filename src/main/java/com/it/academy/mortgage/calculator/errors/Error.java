package com.it.academy.mortgage.calculator.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Error {

    private String message;
    private List<String> errors;

    public Error(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
