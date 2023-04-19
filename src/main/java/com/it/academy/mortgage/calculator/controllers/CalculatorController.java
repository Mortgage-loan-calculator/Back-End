package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
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
@Validated
public class CalculatorController {

    private final CalculateResultService calculateResultService;

    public CalculatorController(CalculateResultService calculateResultService) {
        this.calculateResultService = calculateResultService;
    }

    @PostMapping()
    public ResponseEntity<CalculateResultsDto> sendFormData(@NotNull(message = "o ou") @RequestParam("homePrice") Integer homePrice,
                                                            @Valid @RequestParam("loanTerm") Integer loanTerm) {

        CalculateFormDto calculateFormDto = new CalculateFormDto(homePrice, loanTerm);
        CalculateResultsDto calculateResultsDto = new CalculateResultsDto();
        try {
            return calculateResultService.calculateResults(homePrice, loanTerm);
        } catch (IOException exception) {
            throw new CalculatorException();
        }

        return calculateResultsDto;
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
