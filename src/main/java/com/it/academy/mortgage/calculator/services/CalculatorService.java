package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculatorService extends FormsService {

    public double maxLoan(CalculateFormDto calculateFormDto) {
        return formatDecimal(calculateFormDto.getHomePrice() * 0.85);
    }

    public double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        double partialSum = calculatePartialSum(calculateFormDto.getHomePrice(), calculateFormDto.getLoanTerm());
        double estimatedPayment = partialSum + (partialSum * ((2 + getEuriborRates()) / 100));

        return formatDecimal(estimatedPayment * calculateFormDto.getLoanTerm() * 12);
    }

    public double agreementFee(CalculateFormDto calculateFormDto) {
        return formatDecimal(maxLoan(calculateFormDto) * 0.004);
    }

    public double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal(calculateFormDto.getHomePrice() + agreementFee(calculateFormDto) + totalInterestPaid(calculateFormDto));
    }

}
