package com.it.academy.mortgage.calculator.mappers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.dto.DetailedFormDto;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.models.DetailedForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateMapper implements FormMapper<CalculateForm, CalculateFormDto, CalculateResults, CalculateResultsDto> {

    @Override
    public CalculateForm fromFormDto (CalculateFormDto calculateFormDto) {
        if (calculateFormDto == null) {
            return null;
        }

        CalculateForm calculateForm = new CalculateForm();

//        calculateForm.setId(calculateFormDto.id());
        calculateForm.setHomePrice(calculateFormDto.homePrice());
        calculateForm.setMonthlyFamilyIncome(calculateFormDto.monthlyFamilyIncome());
        calculateForm.setLoanTerm(calculateFormDto.loanTerm());
        calculateForm.setFamilyMembers(calculateFormDto.adults());
        calculateForm.setHaveChildren(calculateFormDto.haveChildren());
        calculateForm.setDetailedForm(calculateForm.getDetailedForm());

        calculateForm.setCalculateResults(fromResultsDto(calculateFormDto.calculateResultsDto()));

        return calculateForm;
    }

    @Override
    public CalculateFormDto toFormDto (CalculateForm calculateForm){
        if (calculateForm == null) {
            return null;
        }

        return new CalculateFormDto(
                calculateForm.getId(),
                calculateForm.getHomePrice(),
                calculateForm.getMonthlyFamilyIncome(),
                calculateForm.getLoanTerm(),
                calculateForm.getFamilyMembers(),
                calculateForm.isHaveChildren(),
                toResultsDto(calculateForm.getCalculateResults()),
                toDetailedDto(calculateForm.getDetailedForm()));
    }

    @Override
    public CalculateResults fromResultsDto (CalculateResultsDto calculateResultsDto){
        if (calculateResultsDto == null) {
            return null;
        }

        CalculateResults calculateResults = new CalculateResults();

//        calculateResults.setId(calculateResultsDto.id());
        calculateResults.setMaxLoan(calculateResultsDto.maxLoan());
        calculateResults.setTotalInterestPaid(calculateResultsDto.totalInterestPaid());
        calculateResults.setAgreementFee(calculateResultsDto.agreementFee());
        calculateResults.setTotalPaymentSum(calculateResultsDto.totalPaymentSum());

        return calculateResults;
    }

    @Override
    public CalculateResultsDto toResultsDto (CalculateResults calculateResults){
        if (calculateResults == null) {
            return null;
        }

        return new CalculateResultsDto(
                calculateResults.getId(),
                calculateResults.getMaxLoan(),
                calculateResults.getTotalInterestPaid(),
                calculateResults.getAgreementFee(),
                calculateResults.getTotalPaymentSum());
    }

    public DetailedFormDto toDetailedDto (DetailedForm detailedForm){
        if (detailedForm == null) {
            return null;
        }

        return new DetailedFormDto(
                detailedForm.getId(),
                detailedForm.getCity(),
                detailedForm.getBuyOption(),
                detailedForm.isStudentLoan(),
                detailedForm.isOtherLoans(),
                detailedForm.getFamilyMembers(),
                detailedForm.isPoliticallyExposed());
    }

    @Override
    public List<CalculateResultsDto> toResultsDtoList (List<CalculateResults> entities){
        List<CalculateResultsDto> dtos = new ArrayList<>();

        for (CalculateResults entity : entities){
            dtos.add(toResultsDto(entity));
        }
        return dtos;
    }

    @Override
    public List<CalculateFormDto> toDtoList (List<CalculateForm> entities){
        List<CalculateFormDto> dtos = new ArrayList<>();

        for (CalculateForm entity : entities){
            dtos.add(toFormDto(entity));
        }
        return dtos;
    }
}
