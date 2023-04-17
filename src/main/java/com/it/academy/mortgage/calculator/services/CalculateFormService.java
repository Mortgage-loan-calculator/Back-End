package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.mappers.CalculateFormMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.repos.CalculateFormRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateFormService {

    private CalculateFormRepository calculateFormRepository;
    private CalculateFormMapper calculateFormMapper;

    public CalculateFormService(CalculateFormRepository calculateFormRepository, CalculateFormMapper calculateFormMapper) {
        this.calculateFormRepository = calculateFormRepository;
        this.calculateFormMapper = calculateFormMapper;
    }

    public CalculateFormDto findById (Long id){
        CalculateForm calculateForm = calculateFormRepository.findById(id).orElse(null);
        return calculateFormMapper.toDto(calculateForm);
    }
    public List<CalculateFormDto> getAllLoanDetailsList(){
        List<CalculateForm> loanDetails = (ArrayList<CalculateForm>) calculateFormRepository.findAll();
        return calculateFormMapper.toDtoList(loanDetails);
    }
    public CalculateFormDto saveLoanDetails (CalculateFormDto calculateFormDto){
        CalculateForm calculateForm = calculateFormMapper.fromDto(calculateFormDto);
        calculateFormRepository.save(calculateForm);
        return calculateFormMapper.toDto(calculateForm);
    }
    public void deleteCalculateForm (Long id){
        calculateFormRepository.deleteById(id);
    }
}
