package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CalculatorService {

    private static double getEuriborRates() throws IOException {
        Document document = Jsoup.connect("https://www.swedbank.lt/private/home/more/pricesrates/loaninterests?language=EN").get();
        Element yearElement = document.select("#mainForm > section > ui-table > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
        return Double.parseDouble(yearElement.text().replace("%", ""));
    }

    public double maxLoan(CalculateFormDto calculateFormDto) {
        return formatDecimal(calculateFormDto.getHomePrice() * 0.85);
    }

    public double sumOfTotalInterestPaid(CalculateFormDto calculateFormDto) {
        return maxLoan(calculateFormDto) / 360;
    }

    public double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal((sumOfTotalInterestPaid(calculateFormDto) +
                (sumOfTotalInterestPaid(calculateFormDto) * ((2 + getEuriborRates()) / 100)))
                * calculateFormDto.getLoanTerm() * 12);
    }

    public double agreementFee(CalculateFormDto calculateFormDto) {
        return formatDecimal(maxLoan(calculateFormDto) * 0.004);
    }

    public double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal(calculateFormDto.getHomePrice() + agreementFee(calculateFormDto) + totalInterestPaid(calculateFormDto));
    }

    static double formatDecimal(double value) {
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);

        return value;
    }

}
