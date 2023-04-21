package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.services.CalculatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("calculate")
@CrossOrigin(origins = {"http://localhost:4200", "https://mortgage-loan-calculator-front-end2.onrender.com"})
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public CalculateResultsDto sendFormData(@Valid @RequestBody CalculateFormDto calculateFormDto) {
        return calculatorService.saveLoanDetailsAndResults(calculateFormDto);
    }

    @GetMapping()
    public CalculateResultsDto getResults(@RequestBody CalculateFormDto calculateFormDto) {
        return calculatorService.calculateResults(calculateFormDto);
    }

    @GetMapping("/forms")
    public List<CalculateFormDto> getAllForms() {
        return calculatorService.getAllLoanDetailsList();
    }

    @GetMapping("/results")
    public List<CalculateResultsDto> getAllResults() {
        return calculatorService.getAllCalculatorResultList();
    }

    @DeleteMapping("/result/{id}")
    public void deleteResult(@PathVariable(name = "id") Long id) {
        calculatorService.deleteCalculateResults(id);
    }

    @DeleteMapping("/form/{id}")
    public void deleteForm(@PathVariable(name = "id") Long id) {
        calculatorService.deleteCalculateForm(id);
    }

}
