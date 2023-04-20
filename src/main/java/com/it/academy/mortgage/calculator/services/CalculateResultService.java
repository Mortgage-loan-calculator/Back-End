package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateResultsMapper;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repositories.CalculateResultsRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateResultService {

    private CalculateResultsRepository calculateResultsRepository;
    private CalculateResultsMapper calculateResultsMapper;
    private CalculateFormService calculateFormService;
    Logger logger = LoggerFactory.getLogger(CalculateResultService.class);

    public CalculateResultService(CalculateResultsRepository calculateResultsRepository, CalculateResultsMapper calculateResultsMapper, CalculateFormService calculateFormService) {
        this.calculateResultsRepository = calculateResultsRepository;
        this.calculateResultsMapper = calculateResultsMapper;
        this.calculateFormService = calculateFormService;
    }

    public CalculateResultsDto calculateResults(int homePrice, int loanTerm) {
        CalculateFormDto calculateFormDto = new CalculateFormDto(homePrice, loanTerm);
        CalculateResultsDto calculateResultsDto = new CalculateResultsDto();

        calculateResultsDto.setMaxLoan(calculateFormService.maxLoan(calculateFormDto));
        try {
            calculateResultsDto.setTotalInterestPaid(calculateFormService.totalInterestPaid(calculateFormDto));
            calculateResultsDto.setAgreementFee(calculateFormService.agreementFee(calculateFormDto));
            calculateResultsDto.setTotalPaymentSum(calculateFormService.totalPaymentSum(calculateFormDto));
        } catch (IOException e) {
            logger.error("Error: " + e);
        }
        return calculateResultsDto;
    }
    public List<CalculateResultsDto> getAllCalculatorResultList() {
        List<CalculateResults> loanResults = (ArrayList<CalculateResults>) calculateResultsRepository.findAll();
        return calculateResultsMapper.toDtoList(loanResults);
    }

    public CalculateResultsDto saveCalculatorResults(CalculateResultsDto calculateResultsDto) {
        CalculateResults calculateResults = calculateResultsMapper.fromDto(calculateResultsDto);
        calculateResultsRepository.save(calculateResults);
        return calculateResultsMapper.toDto(calculateResults);
    }

    public void deleteCalculateForm(Long id) {
        calculateResultsRepository.deleteById(id);
    }

}
