package com.it.academy.mortgage.calculator.models;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CustomerRequest(
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @Length(min = 1, message = "Phone number too short")
        @Length(max = 20, message = "Phone number too long")
        @Digits(integer = 20, fraction = 0, message = "Phone number has to be a number")
        String phoneNumber,
        @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotEmpty(message = "Email cannot be empty")
        String email,
        String action,
        @NotNull
        @Min(value = 0L, message = "familyMembers must be positive")
        Integer familyMembers,
        @NotNull
        Boolean haveChildren,
        @NotNull
        @Min(value = 0L, message = "homePrice must be positive")
        Integer homePrice,
        @NotNull
        @Min(value = 0L, message = "loanTerm must be positive")
        Integer loanTerm,
        @NotNull
        @Min(value = 0L, message = "monthlyFamilyIncome must be positive")
        Integer monthlyFamilyIncome) {
}
