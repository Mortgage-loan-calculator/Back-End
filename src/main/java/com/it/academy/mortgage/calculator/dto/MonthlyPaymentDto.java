package com.it.academy.mortgage.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record MonthlyPaymentDto(@NotNull(message = "partnerToggle cannot be null")
                                Boolean partnerToggle,
                                @NotNull(message = "Deal amount field is mandatory")
                                @PositiveOrZero(message = "Deal amount should be a positive number")
                                Double dealAmount,
                                @NotNull(message = "Down payment field is mandatory")
                                @PositiveOrZero(message = "Down payment should be a positive number")
                                Double downPayment,
                                @NotNull(message = "Loan period field is mandatory")
                                @PositiveOrZero(message = "Loan period should be a positive number")
                                Integer loanPeriod,
                                @NotNull(message = "Monthly family income field is mandatory")
                                @PositiveOrZero(message = "Monthly family income should be a positive number")
                                Integer monthlyIncome) {}
