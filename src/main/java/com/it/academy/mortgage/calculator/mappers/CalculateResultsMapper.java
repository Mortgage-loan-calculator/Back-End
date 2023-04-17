package com.it.academy.mortgage.calculator.mappers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateResultsMapper {

    public CalculateResults fromDto (CalculateResultsDto calculateResultsDto){
        if (calculateResultsDto == null) {
            return null;
        }

        CalculateResults calculateResults = new CalculateResults();

        calculateResults.setId(calculateResultsDto.getId());
        calculateResults.setMaxLoan(calculateResultsDto.getMaxLoan());
        calculateResults.setTotalInterestPaid(calculateResultsDto.getTotalInterestPaid());
        calculateResults.setAgreementFee(calculateResultsDto.getAgreementFee());
        calculateResults.setTotalPaymentSum(calculateResultsDto.getTotalPaymentSum());

        CalculateForm calculateForm = new CalculateForm();
        calculateForm.setId(calculateResultsDto.getId());
        calculateResults.setCalculateForm(calculateForm);

        return calculateResults;
    }

    public CalculateResultsDto toDto (CalculateResults calculateResults){
        if (calculateResults == null) {
            return null;
        }

        CalculateResultsDto dto = new CalculateResultsDto();
        dto.setId(calculateResults.getId());
        dto.setMaxLoan(calculateResults.getMaxLoan());
        dto.setTotalInterestPaid(calculateResults.getTotalInterestPaid());
        dto.setAgreementFee(calculateResults.getAgreementFee());
        dto.setTotalPaymentSum(calculateResults.getTotalPaymentSum());
        dto.setCalculateFormId(calculateResults.getCalculateForm().getId());

        return dto;
    }

    public List<CalculateResultsDto> toDtoList (List<CalculateResults> entities){
        List<CalculateResultsDto> dtos = new ArrayList<>();

        for (CalculateResults entity : entities){
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
