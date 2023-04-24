package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonthlyPaymentService extends FormsService {

    private static final int BANK_INTEREST_RATE = 2;
    private static final double AGREEMENT_FEE = 0.4;

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

}
