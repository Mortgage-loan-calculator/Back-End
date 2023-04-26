package com.it.academy.mortgage.calculator.dto;

import jakarta.validation.constraints.Max;

public record DetailedFormDto (long id,
                               String city,
                               String buyOption,
                               boolean studentLoan,
                               boolean otherLoans,
                               @Max(value = 15, message = "Maximum number of family members is 15")
                               int familyMembers,
                               boolean politicallyExposed) {
}
