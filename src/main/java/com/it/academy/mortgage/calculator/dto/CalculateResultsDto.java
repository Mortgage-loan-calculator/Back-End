package com.it.academy.mortgage.calculator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateResultsDto {

    private Long id;
    private double maxLoan;
    private double totalInterestPaid;
    private double agreementFee;
    private double totalPaymentSum;
}
