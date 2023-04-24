package com.it.academy.mortgage.calculator.dto;

public record CalculateResultsDto(Long id,
                                  double maxLoan,
                                  double totalInterestPaid,
                                  double agreementFee,
                                  double totalPaymentSum) {}
