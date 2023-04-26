package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonthlyPaymentService extends FormsService {

    private static final double LOAN_AGREEMENT_FEE = 0.004;
    private static final double AGREEMENT_FEE = 0.4;
    private static final double LOAN_AMOUNT = 0.85;
    private static final int BANK_INTEREST_RATE = 2;
    private static final int MONTHS_IN_YEAR = 12;

    public double estimatedMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) throws IOException {
        double partialSum = calculatePartialSum(monthlyPaymentDto.dealAmount(), monthlyPaymentDto.loanPeriod());
        double interestRate = (BANK_INTEREST_RATE + fetchEuribor()) / 100;
        double estimatedPayment = partialSum + (partialSum * interestRate);
        if(monthlyPaymentDto.partnerToggle()){
            estimatedPayment = estimatedPayment / 2;
        }
        return formatDecimal(estimatedPayment);
    }

    public double maxMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) {
        double maxMonthlyPayment = formatDecimal(monthlyPaymentDto.monthlyIncome() * AGREEMENT_FEE);
        if(monthlyPaymentDto.partnerToggle()){
            maxMonthlyPayment = maxMonthlyPayment / 2;
        }
        return maxMonthlyPayment;
    }
    public double maxLoan(double homePrice) {
        return formatDecimal(homePrice * LOAN_AMOUNT);
    }

    public double totalInterestPaid(double homePrice, int loanTerm) throws IOException {
        double partialSum = calculatePartialSum(homePrice, loanTerm);
        double estimatedPayment = partialSum + (partialSum * ((BANK_INTEREST_RATE + fetchEuribor()) / 100));

        return formatDecimal(estimatedPayment * loanTerm * MONTHS_IN_YEAR);
    }

    public double agreementFee(double homePrice) {
        return formatDecimal(maxLoan(homePrice) * LOAN_AGREEMENT_FEE);
    }

    public double totalPaymentSum(double homePrice, int loanTerm) throws IOException {
        return formatDecimal(homePrice + agreementFee(homePrice) + totalInterestPaid(homePrice, loanTerm));
    }

}
