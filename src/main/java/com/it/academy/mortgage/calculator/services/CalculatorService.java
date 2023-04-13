package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.models.CalculateFormDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CalculatorService {

    private static double getEuriborRates() throws IOException {
        Document document = Jsoup.connect("https://www.swedbank.lt/private/home/more/pricesrates/loaninterests?language=EN").get();
        Element yearElement = document.select("#mainForm > section > ui-table > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
        return Double.parseDouble(yearElement.text().replace("%",""));
    }

    public double maxLoan(CalculateFormDto calculateFormDto) {
        return calculateFormDto.getMonthlyFamilyIncome() * 12 * 0.4 * calculateFormDto.getLoanTerm();
    }

    public double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        return (2.0 + getEuriborRates()) * maxLoan(calculateFormDto);
    }

    public double agreementFee(CalculateFormDto calculateFormDto) {
        return maxLoan(calculateFormDto) * 0.004;
    }

    public double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return maxLoan(calculateFormDto) + totalInterestPaid(calculateFormDto) + agreementFee(calculateFormDto);
    }
}
