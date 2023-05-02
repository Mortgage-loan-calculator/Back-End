package com.it.academy.mortgage.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

@Validated
public record CalculateFormDto(long id,
                               @NotNull(message = "Home price field is mandatory")
                               @PositiveOrZero(message = "Home price should be a positive number")
                               Integer homePrice,
                               @NotNull(message = "Monthly family income field is mandatory")
                               @PositiveOrZero(message = "Monthly family income should be a positive number")
                               Integer monthlyFamilyIncome,
                               int loanTerm,
                               int familyMembers,
                               boolean haveChildren,
                               CalculateResultsDto calculateResultsDto,
                               DetailedFormDto detailedFormDto) {
}
