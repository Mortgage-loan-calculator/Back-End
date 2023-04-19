package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.exceptions.CalculatorException;
import com.it.academy.mortgage.calculator.services.CalculateFormService;
import com.it.academy.mortgage.calculator.services.CalculateResultService;
import com.it.academy.mortgage.calculator.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<CalculateResultsDto> saveResultsData(@RequestBody CalculateResultsDto resultsDto) {
        calculateResultService.saveCalculatorResults(resultsDto);
        return ResponseEntity.ok(resultsDto);
    }

    @GetMapping()
    public CalculateResultsDto sendFormData(@RequestParam("homePrice") int homePrice,
                                            @RequestParam("loanTerm") int loanTerm) {

        return calculateResultService.calculateResults(homePrice, loanTerm);
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
