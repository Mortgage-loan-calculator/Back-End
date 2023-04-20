package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateFormMapper;
import com.it.academy.mortgage.calculator.mappers.CalculateResultsMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repositories.CalculateFormRepository;
import com.it.academy.mortgage.calculator.repositories.CalculateResultsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService extends FormsService{

    private CalculateFormRepository calculateFormRepository;
    private CalculateResultsRepository calculateResultsRepository;
    private CalculateFormMapper calculateFormMapper;
    private CalculateResultsMapper calculateResultsMapper;

    public CalculatorService(CalculateFormRepository calculateFormRepository, CalculateResultsRepository calculateResultsRepository, CalculateFormMapper calculateFormMapper, CalculateResultsMapper calculateResultsMapper) {
        this.calculateFormRepository = calculateFormRepository;
        this.calculateResultsRepository = calculateResultsRepository;
        this.calculateFormMapper = calculateFormMapper;
        this.calculateResultsMapper = calculateResultsMapper;
    }
    Logger logger = LoggerFactory.getLogger(CalculatorService.class);


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

    public CalculateFormDto findById (Long id){
        CalculateForm calculateForm = calculateFormRepository.findById(id).orElse(null);
        return calculateFormMapper.toDto(calculateForm);
    }
    public List<CalculateFormDto> getAllLoanDetailsList(){
        List<CalculateForm> loanDetails = calculateFormRepository.findAll();
        return calculateFormMapper.toDtoList(loanDetails);
    }
    public CalculateResultsDto saveLoanDetailsAndResults (CalculateFormDto calculateFormDto) {

        CalculateResults calculateResults = new CalculateResults();
        calculateResults.setMaxLoan(maxLoan(calculateFormDto));
        try {
            calculateResults.setTotalInterestPaid(totalInterestPaid(calculateFormDto));
            calculateResults.setAgreementFee(agreementFee(calculateFormDto));
            calculateResults.setTotalPaymentSum(totalPaymentSum(calculateFormDto));
        } catch (IOException e) {
            logger.error("Error: " + e);;
        }

        calculateFormDto.setCalculateResults(calculateResults);

        CalculateForm calculateForm = calculateFormMapper.fromDto(calculateFormDto);
        calculateFormRepository.save(calculateForm);
        calculateResultsRepository.save(calculateResults);
        return calculateResultsMapper.toDto(calculateResults);
    }

    public List<CalculateResultsDto> getAllCalculatorResultList() {
        List<CalculateResults> loanResults = calculateResultsRepository.findAll();
        return calculateResultsMapper.toDtoList(loanResults);
    }
    public void deleteCalculateForm (Long id){
        calculateFormRepository.deleteById(id);
    }
    public void deleteCalculateResults(Long id) {
        calculateResultsRepository.deleteById(id);
    }
}
