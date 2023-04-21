package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repositories.CalculateFormRepository;
import com.it.academy.mortgage.calculator.repositories.CalculateResultsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CalculatorService extends FormsService {

    public static final double AGREEMENT_FEE = 0.004;
    public static final double LOAN_AMOUNT = 0.85;
    public static final int BANK_INTEREST_RATE = 2;
    public static final int MONTHS_IN_YEAR = 12;
    private final CalculateFormRepository calculateFormRepository;
    private final CalculateResultsRepository calculateResultsRepository;
    private final CalculateMapper calculateMapper;
    Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

    public CalculatorService(CalculateFormRepository calculateFormRepository, CalculateResultsRepository calculateResultsRepository, CalculateMapper calculateMapper) {
        this.calculateFormRepository = calculateFormRepository;
        this.calculateResultsRepository = calculateResultsRepository;
        this.calculateMapper = calculateMapper;
    }

    private double maxLoan(CalculateFormDto calculateFormDto) {
        return formatDecimal(calculateFormDto.getHomePrice() * LOAN_AMOUNT);
    }

    private double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        double partialSum = calculatePartialSum(calculateFormDto.getHomePrice(), calculateFormDto.getLoanTerm());
        double estimatedPayment = partialSum + (partialSum * ((BANK_INTEREST_RATE + getEuriborRates()) / 100));

        return formatDecimal(estimatedPayment * calculateFormDto.getLoanTerm() * MONTHS_IN_YEAR);
    }

    private double agreementFee(CalculateFormDto calculateFormDto) {
        return formatDecimal(maxLoan(calculateFormDto) * AGREEMENT_FEE);
    }

    private double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal(calculateFormDto.getHomePrice() + agreementFee(calculateFormDto) + totalInterestPaid(calculateFormDto));
    }

    public CalculateFormDto findById(Long id) {
        CalculateForm calculateForm = calculateFormRepository.findById(id).orElse(null);
        return calculateMapper.toFormDto(calculateForm);
    }

    public List<CalculateFormDto> getAllLoanDetailsList() {
        List<CalculateForm> loanDetails = calculateFormRepository.findAll();
        return calculateMapper.toDtoList(loanDetails);
    }

    public CalculateResultsDto saveLoanDetailsAndResults(CalculateFormDto calculateFormDto) {

        CalculateResultsDto calculateResultsDto = calculateResults(calculateFormDto);

        calculateFormDto.setCalculateResultsDto(calculateResultsDto);

        CalculateForm calculateForm = calculateMapper.fromFormDto(calculateFormDto);
        CalculateResults calculateResults = calculateMapper.fromResultsDto(calculateResultsDto);
        calculateFormRepository.save(calculateForm);
        calculateResultsRepository.save(calculateResults);
        return calculateMapper.toResultsDto(calculateResults);
    }

    public CalculateResultsDto calculateResults(CalculateFormDto calculateFormDto) {
        CalculateResultsDto calculateResultsDto = new CalculateResultsDto();
        calculateResultsDto.setMaxLoan(maxLoan(calculateFormDto));
        try {
            calculateResultsDto.setTotalInterestPaid(totalInterestPaid(calculateFormDto));
            calculateResultsDto.setAgreementFee(agreementFee(calculateFormDto));
            calculateResultsDto.setTotalPaymentSum(totalPaymentSum(calculateFormDto));
        } catch (IOException e) {
            LOGGER.error("Error: " + e);
        }
        return calculateResultsDto;
    }

    public List<CalculateResultsDto> getAllCalculatorResultList() {
        List<CalculateResults> loanResults = calculateResultsRepository.findAll();
        return calculateMapper.toResultsDtoList(loanResults);
    }

    public void deleteCalculateForm(Long id) {
        calculateFormRepository.deleteById(id);
    }

    public void deleteCalculateResults(Long id) {
        calculateResultsRepository.deleteById(id);
    }
}
