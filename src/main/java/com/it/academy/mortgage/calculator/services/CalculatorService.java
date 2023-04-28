package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.mappers.CalculateMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.repositories.CalculateFormRepository;
import com.it.academy.mortgage.calculator.repositories.CalculateResultsRepository;
import com.it.academy.mortgage.calculator.services.enums.LoanState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CalculatorService extends FormsService {

    private static final double AGREEMENT_FEE = 0.004;
    private static final int BANK_INTEREST_RATE = 2;
    private static final int MONTHS_IN_YEAR = 12;
    protected final CalculateFormRepository calculateFormRepository;
    protected final CalculateResultsRepository calculateResultsRepository;
    protected final CalculateMapper calculateMapper;
    protected final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

    public CalculatorService(CalculateFormRepository calculateFormRepository, CalculateResultsRepository calculateResultsRepository, CalculateMapper calculateMapper) {
        this.calculateFormRepository = calculateFormRepository;
        this.calculateResultsRepository = calculateResultsRepository;
        this.calculateMapper = calculateMapper;
    }

    private double maxLoan(CalculateFormDto calculateFormDto) {
        if (calculateFormDto.detailedFormDto() != null) {
            return formatDecimal(
                    calculateFormDto.homePrice() * LoanState.valueOf(calculateFormDto.detailedFormDto().buyOption()).getLoanAmount());
        } else {
            return formatDecimal(calculateFormDto.homePrice() * LoanState.NEW_HOUSE.getLoanAmount());
        }
    }

    private double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        double partialSum = calculatePartialSum(calculateFormDto.homePrice(), calculateFormDto.loanTerm());
        double estimatedPayment = partialSum + (partialSum * ((BANK_INTEREST_RATE + fetchEuribor()) / 100));

        return formatDecimal(estimatedPayment * calculateFormDto.loanTerm() * MONTHS_IN_YEAR);
    }

    private double agreementFee(CalculateFormDto calculateFormDto) {
        return formatDecimal(maxLoan(calculateFormDto) * AGREEMENT_FEE);
    }

    private double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal(calculateFormDto.homePrice() + agreementFee(calculateFormDto) + totalInterestPaid(calculateFormDto));
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

        CalculateFormDto calculateFormDtoNew =
                new CalculateFormDto(
                        calculateFormDto.id(),
                        calculateFormDto.homePrice(),
                        calculateFormDto.monthlyFamilyIncome(),
                        calculateFormDto.loanTerm(),
                        calculateFormDto.adults(),
                        calculateFormDto.haveChildren(),
                        calculateResultsDto,
                        calculateFormDto.detailedFormDto()
                );

        CalculateForm calculateForm = calculateMapper.fromFormDto(calculateFormDtoNew);
        CalculateResults calculateResults = calculateMapper.fromResultsDto(calculateResultsDto);

        calculateFormRepository.save(calculateForm);
        calculateResultsRepository.save(calculateResults);
        return calculateMapper.toResultsDto(calculateResults);
    }

    public CalculateResultsDto calculateResults(CalculateFormDto calculateFormDto) {
        CalculateResultsDto calculateResultsDto = null;
        try {
            calculateResultsDto = new CalculateResultsDto(
                    calculateFormDto.id(),
                    maxLoan(calculateFormDto),
                    totalInterestPaid(calculateFormDto),
                    agreementFee(calculateFormDto),
                    totalPaymentSum(calculateFormDto));
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
