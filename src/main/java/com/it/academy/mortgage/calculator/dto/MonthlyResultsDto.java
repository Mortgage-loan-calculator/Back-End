package com.it.academy.mortgage.calculator.dto;

public class MonthlyResultsDto {

    private double estimatedMonthlyPayment;
    private double maxMonthlyPayment;

    public double getEstimatedMonthlyPayment() {
        return estimatedMonthlyPayment;
    }

    public void setEstimatedMonthlyPayment(double estimatedMonthlyPayment) {
        this.estimatedMonthlyPayment = estimatedMonthlyPayment;
    }

    public double getMaxMonthlyPayment() {
        return maxMonthlyPayment;
    }

    public void setMaxMonthlyPayment(double maxMonthlyPayment) {
        this.maxMonthlyPayment = maxMonthlyPayment;
    }
}
