package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonthlyPaymentService extends FormsService {

    public double estimatedMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) throws IOException {
        double partialSum = calculatePartialSum(monthlyPaymentDto.getDealAmount(), monthlyPaymentDto.getLoanPeriod());
        double interestRate = (2 + getEuriborRates()) / 100;
        double estimatedPayment = partialSum + (partialSum * interestRate);

        return formatDecimal(estimatedPayment);
    }

    public double maxMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) {
        return formatDecimal(monthlyPaymentDto.getMonthlyIncome() * 0.4);
    }

}
