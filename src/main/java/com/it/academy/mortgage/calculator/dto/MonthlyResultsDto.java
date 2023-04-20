package com.it.academy.mortgage.calculator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyResultsDto {

    private double estimatedMonthlyPayment;
    private double maxMonthlyPayment;
}
