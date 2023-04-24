package com.it.academy.mortgage.calculator.services;

public class FormsService extends EuriborFetcher {

    private static final double LOAN_AMOUNT = 0.85;
    private static final int MONTHS_IN_A_YEAR = 12;

    public double calculatePartialSum(double homePrice, int loanPeriod) {
        return (homePrice * LOAN_AMOUNT) / (loanPeriod * MONTHS_IN_A_YEAR);
    }

    public double formatDecimal(double value) {
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);

        return value;
    }
}
