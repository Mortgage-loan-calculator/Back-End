package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.MonthlyPaymentDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MonthlyPaymentService {

    public static double getEuriborRates() throws IOException {
        Document document = Jsoup.connect("https://www.swedbank.lt/private/home/more/pricesrates/loaninterests?language=EN").get();
        Element yearElement = document.select("#mainForm > section > ui-table > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
        return Double.parseDouble(yearElement.text().replace("%", ""));
    }

    public double partialSum(MonthlyPaymentDto monthlyPaymentDto) {
        return (monthlyPaymentDto.getDealAmount() - monthlyPaymentDto.getDownPayment()) / (monthlyPaymentDto.getLoanPeriod() * 12);
    }

    public double estimatedMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) throws IOException {
        return formatDecimal(partialSum(monthlyPaymentDto) + (partialSum(monthlyPaymentDto) * ((2 + getEuriborRates()) / 100)));
    }

    public double maxMonthlyPayment(MonthlyPaymentDto monthlyPaymentDto) {
        return formatDecimal(monthlyPaymentDto.getMonthlyIncome() * 0.4);
    }

    static double formatDecimal(double value) {
        value = value * Math.pow(10, 2);
        value = Math.floor(value);
        value = value / Math.pow(10, 2);

        return value;
    }
}
