package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonthlyPaymentService extends FormsService {

    public static final int BANK_INTEREST_RATE = 2;
    public static final double AGREEMENT_FEE = 0.4;

    public double estimatedMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) throws IOException {
        double partialSum = calculatePartialSum(monthlyPaymentDto.getDealAmount(), monthlyPaymentDto.getLoanPeriod());
        double interestRate = (BANK_INTEREST_RATE + getEuriborRates()) / 100;
        double estimatedPayment = partialSum + (partialSum * interestRate);

        return formatDecimal(estimatedPayment);
    }

    public double maxMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) {
        return formatDecimal(monthlyPaymentDto.getMonthlyIncome() * AGREEMENT_FEE);
    }

}
