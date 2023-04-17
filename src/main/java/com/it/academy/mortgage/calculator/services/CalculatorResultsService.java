package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateResultsMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repos.CalculateResultsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorResultsService {

    private CalculateResultsRepository calculateResultsRepository;
    private CalculateResultsMapper calculateResultsMapper;

    public CalculatorResultsService(CalculateResultsRepository calculateResultsRepository, CalculateResultsMapper calculateResultsMapper) {
        this.calculateResultsRepository = calculateResultsRepository;
        this.calculateResultsMapper = calculateResultsMapper;
    }

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

    public List<CalculateResultsDto> getAllCalculatorResultList(){
        List<CalculateResults> loanResults = (ArrayList<CalculateResults>) calculateResultsRepository.findAll();
        return calculateResultsMapper.toDtoList(loanResults);
    }
    public CalculateResultsDto saveCalculatorResults (CalculateResultsDto calculateResultsDto){
        CalculateResults calculateResults = calculateResultsMapper.fromDto(calculateResultsDto);
        calculateResultsRepository.save(calculateResults);
        return calculateResultsMapper.toDto(calculateResults);
    }
    public void deleteCalculateForm (Long id){
        calculateResultsRepository.deleteById(id);
    }

}
