package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.dto.DetailedFormDto;
import com.it.academy.mortgage.calculator.mappers.CalculateMapper;
import com.it.academy.mortgage.calculator.mappers.DetailedMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.models.DetailedForm;
import com.it.academy.mortgage.calculator.repositories.CalculateFormRepository;
import com.it.academy.mortgage.calculator.repositories.CalculateResultsRepository;
import com.it.academy.mortgage.calculator.repositories.DetailedFormRepository;
import com.it.academy.mortgage.calculator.services.enums.LoanState;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DetailedCalculatorService extends CalculatorService {

    private static final double AGREEMENT_FEE = 0.004;
    private static final int BANK_INTEREST_RATE = 2;
    private static final int MONTHS_IN_YEAR = 12;
    private final DetailedMapper detailedMapper;
    private final DetailedFormRepository detailedFormRepository;

    public DetailedCalculatorService(CalculateFormRepository calculateFormRepository,
                                     CalculateResultsRepository calculateResultsRepository,
                                     CalculateMapper calculateMapper,
                                     DetailedMapper detailedMapper,
                                     DetailedFormRepository detailedFormRepository) {
        super(calculateFormRepository, calculateResultsRepository, calculateMapper);
        this.detailedMapper = detailedMapper;
        this.detailedFormRepository = detailedFormRepository;
    }

    private double maxLoanDetailed(CalculateFormDto calculateFormDto) {
        LoanState loanState = LoanState.valueOf(calculateFormDto.detailedFormDto().buyOption());
        return formatDecimal(calculateFormDto.homePrice() * loanState.getLoanAmount());
    }

    public CalculateResultsDto calculateDetailedResults(CalculateFormDto calculateFormDto) {
        CalculateResultsDto calculateResultsDto = null;
        try {
            calculateResultsDto = new CalculateResultsDto(
                    calculateFormDto.id(),
                    maxLoanDetailed(calculateFormDto),
                    totalInterestPaid(calculateFormDto),
                    agreementFee(calculateFormDto),
                    totalPaymentSum(calculateFormDto));
        } catch (IOException e) {
            LOGGER.error("Error: " + e);
        }
        return calculateResultsDto;
    }

    public CalculateResultsDto saveLoanDetailsAndResults(CalculateFormDto calculateFormDto) {

        CalculateResultsDto calculateResultsDto = calculateDetailedResults(calculateFormDto);

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

        DetailedForm detailedForm = saveDetailedResults(calculateFormDto);
        calculateForm.setDetailedForm(detailedForm);

        calculateFormRepository.save(calculateForm);
        calculateResultsRepository.save(calculateResults);
        return calculateMapper.toResultsDto(calculateResults);
    }

    public DetailedForm saveDetailedResults(CalculateFormDto calculateFormDto) {
        DetailedFormDto detailedFormDto = new DetailedFormDto(
                calculateFormDto.detailedFormDto().id(),
                calculateFormDto.detailedFormDto().city(),
                calculateFormDto.detailedFormDto().buyOption(),
                calculateFormDto.detailedFormDto().studentLoan(),
                calculateFormDto.detailedFormDto().otherLoans(),
                calculateFormDto.detailedFormDto().familyMembers(),
                calculateFormDto.detailedFormDto().politicallyExposed());

        DetailedForm detailedForm = detailedMapper.fromFormDto(detailedFormDto);
        detailedFormRepository.save(detailedForm);
        return detailedForm;
    }

    private double totalInterestPaid(CalculateFormDto calculateFormDto) throws IOException {
        double partialSum = calculatePartialSum(calculateFormDto.homePrice(), calculateFormDto.loanTerm());
        double estimatedPayment = partialSum + (partialSum * ((BANK_INTEREST_RATE + fetchEuribor()) / 100));

        return formatDecimal(estimatedPayment * calculateFormDto.loanTerm() * MONTHS_IN_YEAR);
    }

    private double agreementFee(CalculateFormDto calculateFormDto) {
        return formatDecimal(maxLoanDetailed(calculateFormDto) * AGREEMENT_FEE);
    }

    private double totalPaymentSum(CalculateFormDto calculateFormDto) throws IOException {
        return formatDecimal(calculateFormDto.homePrice() + agreementFee(calculateFormDto) + totalInterestPaid(calculateFormDto));
    }

    public List<DetailedFormDto> getDetailedDto() {
        List<DetailedForm> detailedForms = detailedFormRepository.findAll();
        return detailedMapper.toDtoList(detailedForms);
    }

    public void deleteDetailedForm(Long id) {
        detailedFormRepository.deleteById(id);
    }

}
