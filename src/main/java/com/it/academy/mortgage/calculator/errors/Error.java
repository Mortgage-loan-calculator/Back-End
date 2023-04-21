package com.it.academy.mortgage.calculator.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Error {

    private String message;
    private List<String> errors;

    public Error(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
