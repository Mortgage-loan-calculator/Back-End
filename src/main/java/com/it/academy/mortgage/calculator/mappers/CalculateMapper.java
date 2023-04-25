package com.it.academy.mortgage.calculator.mappers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateMapper {

    public CalculateForm fromFormDto (CalculateFormDto calculateFormDto) {
        if (calculateFormDto == null) {
            return null;
        }

        CalculateForm calculateForm = new CalculateForm();

//        calculateForm.setId(calculateFormDto.id());
        calculateForm.setHomePrice(calculateFormDto.homePrice());
        calculateForm.setMonthlyFamilyIncome(calculateFormDto.monthlyFamilyIncome());
        calculateForm.setLoanTerm(calculateFormDto.loanTerm());
        calculateForm.setFamilyMembers(calculateFormDto.familyMembers());
        calculateForm.setHaveChildren(calculateFormDto.haveChildren());
        calculateForm.setCity(calculateFormDto.city());

        calculateForm.setCalculateResults(fromResultsDto(calculateFormDto.calculateResultsDto()));

        return calculateForm;
    }

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
                calculateForm.getCity(),
                toResultsDto(calculateForm.getCalculateResults()));
    }

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

    public List<CalculateResultsDto> toResultsDtoList (List<CalculateResults> entities){
        List<CalculateResultsDto> dtos = new ArrayList<>();

        for (CalculateResults entity : entities){
            dtos.add(toResultsDto(entity));
        }
        return dtos;
    }

    public List<CalculateFormDto> toDtoList (List<CalculateForm> entities){
        List<CalculateFormDto> dtos = new ArrayList<>();

        for (CalculateForm entity : entities){
            dtos.add(toFormDto(entity));
        }
        return dtos;
    }
}
