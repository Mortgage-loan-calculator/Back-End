package com.it.academy.mortgage.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class CalculateFormDto {

    private long id;
    private int homePrice;
    private int monthlyFamilyIncome;
    private int loanTerm;
    private int familyMembers;
    private boolean haveChildren;
    private String city;

    public CalculateFormDto(){}

    public CalculateFormDto(int homePrice, int monthlyFamilyIncome, int loanTerm) {
        this.homePrice = homePrice;
        this.monthlyFamilyIncome = monthlyFamilyIncome;
        this.loanTerm = loanTerm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(int homePrice) {
        this.homePrice = homePrice;
    }

    public int getMonthlyFamilyIncome() {
        return monthlyFamilyIncome;
    }

    public void setMonthlyFamilyIncome(int monthlyFamilyIncome) {
        this.monthlyFamilyIncome = monthlyFamilyIncome;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public double getMaxLoan() {
//        return maxLoan;
//    }
//
//    public void setMaxLoan(double maxLoan) {
//        this.maxLoan = maxLoan;
//    }
//
//    public double getTotalInterestPaid() {
//        return totalInterestPaid;
//    }
//
//    public void setTotalInterestPaid(double totalInterestPaid) {
//        this.totalInterestPaid = totalInterestPaid;
//    }
//
//    public double getAgreementFee() {
//        return agreementFee;
//    }
//
//    public void setAgreementFee(double agreementFee) {
//        this.agreementFee = agreementFee;
//    }
//
//    public double getTotalPaymentSum() {
//        return totalPaymentSum;
//    }
//
//    public void setTotalPaymentSum(double totalPaymentSum) {
//        this.totalPaymentSum = totalPaymentSum;
//    }
}
