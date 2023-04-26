package com.it.academy.mortgage.calculator.models;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CustomerRequest(String id,
                              @NotEmpty(message = "Name cannot be empty")
                              String name,
                              @Length(max = 20, message = "Phone number too long")
                              @Digits(integer = 20, fraction = 0, message = "Phone number has to be a number")
                              String phoneNumber,
                              @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                              @NotEmpty(message = "Email cannot be empty")
                              String email,
                              String action,
                              CalculateFormDto calculateFormDto) {
}
