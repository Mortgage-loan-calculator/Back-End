package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.services.CalculatorResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("calculate")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculatorController {

    private final CalculatorResultsService calculatorResultsService;

    public CalculatorController(CalculatorResultsService calculatorResultsService) {
        this.calculatorResultsService = calculatorResultsService;
    }

    Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping
    public ResponseEntity<CalculateFormDto> getFormData(@RequestBody CalculateFormDto formData) {
        return ResponseEntity.ok(formData);
    }

    @GetMapping()
    public CalculateResultsDto sendFormData(@RequestParam("homePrice") int homePrice,
                                         @RequestParam("monthlyIncome") int monthlyIncome,
                                         @RequestParam("loanTerm") int loanTerm) {

        CalculateFormDto calculateFormDto = new CalculateFormDto(homePrice, monthlyIncome, loanTerm);
        CalculateResultsDto calculateResultsDto = new CalculateResultsDto();
        try {

            calculateResultsDto.setMaxLoan(calculatorResultsService.maxLoan(calculateFormDto));
            calculateResultsDto.setTotalInterestPaid(calculatorResultsService.totalInterestPaid(calculateFormDto));
            calculateResultsDto.setAgreementFee(calculatorResultsService.agreementFee(calculateFormDto));
            calculateResultsDto.setTotalPaymentSum(calculatorResultsService.totalPaymentSum(calculateFormDto));
        } catch (IOException exception) {
            logger.error("Error: " + exception);
        }

        return calculateResultsDto;
    }
}
