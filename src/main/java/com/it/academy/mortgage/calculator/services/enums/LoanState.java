package com.it.academy.mortgage.calculator.services.enums;

public enum LoanState {
    NEW_HOUSE(0.85),
    COOPERATIVE(0.85),
    SECOND_HOUSE(0.80);

    private final double loanAmount;

    LoanState(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanAmount(){
        return loanAmount;
    }
}
