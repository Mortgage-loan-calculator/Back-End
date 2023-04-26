package com.it.academy.mortgage.calculator.mappers;

import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.dto.DetailedFormDto;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import com.it.academy.mortgage.calculator.models.DetailedForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailedMapper implements FormMapper<DetailedForm, DetailedFormDto, CalculateResults, CalculateResultsDto> {

    @Override
    public DetailedForm fromFormDto(DetailedFormDto dto) {
        if (dto == null) {
            return null;
        }

        DetailedForm detailedForm = new DetailedForm();
//        detailedForm.setId(dto.id());
        detailedForm.setCity(dto.city());
        detailedForm.setBuyOption(dto.buyOption());
        detailedForm.setOtherLoans(dto.otherLoans());
        detailedForm.setPoliticallyExposed(dto.politicallyExposed());
        detailedForm.setStudentLoan(dto.studentLoan());
        detailedForm.setFamilyMembers(dto.familyMembers());

        return detailedForm;
    }

    @Override
    public DetailedFormDto toFormDto(DetailedForm entity) {
        if (entity == null) {
            return null;
        }

        return new DetailedFormDto(
                entity.getId(),
                entity.getCity(),
                entity.getBuyOption(),
                entity.isStudentLoan(),
                entity.isOtherLoans(),
                entity.getFamilyMembers(),
                entity.isPoliticallyExposed());
    }

    @Override
    public CalculateResults fromResultsDto(CalculateResultsDto resultsDto) {
        return null;
    }

    @Override
    public CalculateResultsDto toResultsDto(CalculateResults results) {
        return null;
    }

    @Override
    public List<CalculateResultsDto> toResultsDtoList(List<CalculateResults> results) {
        return null;
    }

    @Override
    public List<DetailedFormDto> toDtoList(List<DetailedForm> entities) {
        List<DetailedFormDto> dtos = new ArrayList<>();

        for (DetailedForm entity : entities){
            dtos.add(toFormDto(entity));
        }
        return dtos;
    }
}
