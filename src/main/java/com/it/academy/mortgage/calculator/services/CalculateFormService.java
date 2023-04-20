package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.controllers.CalculatorController;
import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateFormMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repositories.CalculateFormRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CalculateFormService extends FormsService {

    private CalculateFormRepository calculateFormRepository;
    private CalculateFormMapper calculateFormMapper;

    public CalculateFormService(CalculateFormRepository calculateFormRepository, CalculateFormMapper calculateFormMapper) {
        this.calculateFormRepository = calculateFormRepository;
        this.calculateFormMapper = calculateFormMapper;
    }
    Logger logger = LoggerFactory.getLogger(CalculateFormService.class);


    public double maxLoan(CalculateFormDto calculateFormDto) {
        return formatDecimal(calculateFormDto.getHomePrice() * 0.85);
    }

    public double sumOfTotalInterestPaid(CalculateFormDto calculateFormDto) {
        return maxLoan(calculateFormDto) / (calculateFormDto.getLoanTerm() * 12);
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

    public CalculateFormDto findById (Long id){
        CalculateForm calculateForm = calculateFormRepository.findById(id).orElse(null);
        return calculateFormMapper.toDto(calculateForm);
    }
    public List<CalculateFormDto> getAllLoanDetailsList(){
        List<CalculateForm> loanDetails = calculateFormRepository.findAll();
        return calculateFormMapper.toDtoList(loanDetails);
    }
    public CalculateFormDto saveLoanDetails (CalculateFormDto calculateFormDto) {

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
        System.out.println(calculateForm);
        return calculateFormMapper.toDto(calculateForm);
    }
    public void deleteCalculateForm (Long id){
        calculateFormRepository.deleteById(id);
    }
}
