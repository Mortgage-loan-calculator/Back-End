package com.it.academy.mortgage.calculator.mappers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateFormMapper {

    public CalculateForm fromDto (CalculateFormDto calculateFormDto){
        if (calculateFormDto == null) {
            return null;
        }

        CalculateForm calculateForm = new CalculateForm();

        calculateForm.setId(calculateFormDto.getId());
        calculateForm.setHomePrice(calculateFormDto.getHomePrice());
        calculateForm.setMonthlyFamilyIncome(calculateFormDto.getMonthlyFamilyIncome());
        calculateForm.setLoanTerm(calculateFormDto.getLoanTerm());
        calculateForm.setFamilyMembers(calculateFormDto.getFamilyMembers());
        calculateForm.setHaveChildren(calculateFormDto.isHaveChildren());
        calculateForm.setCity(calculateFormDto.getCity());

        return calculateForm;
    }

    public CalculateFormDto toDto (CalculateForm calculateForm){
        if (calculateForm == null) {
            return null;
        }

        CalculateFormDto dto = new CalculateFormDto();
        dto.setId(calculateForm.getId());
        dto.setHomePrice(calculateForm.getHomePrice());
        dto.setMonthlyFamilyIncome(calculateForm.getMonthlyFamilyIncome());
        dto.setLoanTerm(calculateForm.getLoanTerm());
        dto.setFamilyMembers(calculateForm.getFamilyMembers());
        dto.setHaveChildren(calculateForm.isHaveChildren());
        dto.setCity(calculateForm.getCity());

        return dto;
    }

    public List<CalculateFormDto> toDtoList (List<CalculateForm> entities){
        List<CalculateFormDto> dtos = new ArrayList<>();

        for (CalculateForm entity : entities){
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
