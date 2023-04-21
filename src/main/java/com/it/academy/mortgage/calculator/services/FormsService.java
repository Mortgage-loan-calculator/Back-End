package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FormsService {

    private static final double LOAN_AMOUNT = 0.85;
    private static final int MONTHS_IN_A_YEAR = 12;

    public double getEuriborRates() throws IOException {
        Document document = Jsoup.connect("https://www.swedbank.lt/private/home/more/pricesrates/loaninterests?language=EN").get();
        Element yearElement = document.select("#mainForm > section > ui-table > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
        return Double.parseDouble(yearElement.text().replace("%", ""));
    }

    public double calculatePartialSum(double homePrice, int loanPeriod) {
        return (homePrice * LOAN_AMOUNT) / (loanPeriod * MONTHS_IN_A_YEAR);
    }

    public double formatDecimal(double value) {
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);

        return value;
    }
}
