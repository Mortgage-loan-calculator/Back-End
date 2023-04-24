package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonthlyPaymentService extends FormsService {

    private static final int BANK_INTEREST_RATE = 2;
    private static final double AGREEMENT_FEE = 0.4;

    public double estimatedMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) throws IOException {

        double partialSum = calculatePartialSum(monthlyPaymentDto.getDealAmount(), monthlyPaymentDto.getLoanPeriod());
        double interestRate = (BANK_INTEREST_RATE + getEuriborRates()) / 100;
        double estimatedPayment = partialSum + (partialSum * interestRate);
        if(monthlyPaymentDto.getPartnerToggle()){
            estimatedPayment = estimatedPayment / 2;
        }
        return formatDecimal(estimatedPayment);
    }

    public double maxMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) {
        double maxMonthlyPayment = formatDecimal(monthlyPaymentDto.getMonthlyIncome() * AGREEMENT_FEE);
        if(monthlyPaymentDto.getPartnerToggle()){
            maxMonthlyPayment = maxMonthlyPayment / 2;
        }
        return maxMonthlyPayment;
    }

}
