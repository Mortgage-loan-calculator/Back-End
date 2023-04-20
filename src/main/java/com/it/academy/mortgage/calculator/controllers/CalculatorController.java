package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
import com.it.academy.mortgage.calculator.services.CalculateResultService;
import com.it.academy.mortgage.calculator.services.CalculatorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("calculate")
@CrossOrigin(origins = {"http://localhost:4200", "https://mortgage-loan-calculator-front-end2.onrender.com"})
public class CalculatorController {

    private final CalculateResultService calculateResultService;

    public CalculatorController(CalculateResultService calculateResultService) {
        this.calculateResultService = calculateResultService;
    }

    @PostMapping()
    public CalculateResultsDto sendFormData(@Valid @RequestBody CalculateFormDto calculateFormDto) {
        return calculateResultService.calculateResults(calculateFormDto.getHomePrice(), calculateFormDto.getLoanTerm());
    }

    @GetMapping("/all")
    public List<CalculateResultsDto> getAllResults(){
        return calculateResultService.getAllCalculatorResultList();
    }

    @DeleteMapping("{id}")
    public void deletePatient (@PathVariable (name = "id") Long id){
        calculateResultService.deleteCalculateForm(id);
    }
}
