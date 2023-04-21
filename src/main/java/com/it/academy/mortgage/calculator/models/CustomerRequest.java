package com.it.academy.mortgage.calculator.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        String name,
        String phoneNumber,
        String email,
        String action) {
}
