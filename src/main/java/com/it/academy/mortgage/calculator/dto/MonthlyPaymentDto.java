package com.it.academy.mortgage.calculator.dto;

public class MonthlyPaymentDto {

    private double dealAmount;
    private double downPayment;
    private int loanPeriod;
    private int monthlyIncome;

    public MonthlyPaymentDto(double dealAmount, double downPayment, int loanPeriod, int monthlyIncome) {
        this.dealAmount = dealAmount;
        this.downPayment = downPayment;
        this.loanPeriod = loanPeriod;
        this.monthlyIncome = monthlyIncome;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
