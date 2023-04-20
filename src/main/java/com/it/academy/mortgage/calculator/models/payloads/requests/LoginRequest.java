package com.it.academy.mortgage.calculator.models.payloads.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    @NotBlank
    String username,

    @NotBlank
    String password
){}
