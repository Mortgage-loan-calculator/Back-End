package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.dto.CalculateResultsDto;
import com.it.academy.mortgage.calculator.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("calculate")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculatorController {

    private final CalculatorService calculatorService = new CalculatorService();
    Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping
    public ResponseEntity<CalculateFormDto> getFormData(@RequestBody CalculateFormDto formData) {
        return ResponseEntity.ok(formData);
    }

    @GetMapping()
    public CalculateResultsDto sendFormData(@RequestParam("homePrice") int homePrice,
                                         @RequestParam("loanTerm") int loanTerm) {

        CalculateFormDto calculateFormDto = new CalculateFormDto(homePrice, loanTerm);
        CalculateResultsDto calculateResultsDto = new CalculateResultsDto();
        try {

            calculateResultsDto.setMaxLoan(calculatorService.maxLoan(calculateFormDto));
            calculateResultsDto.setTotalInterestPaid(calculatorService.totalInterestPaid(calculateFormDto));
            calculateResultsDto.setAgreementFee(calculatorService.agreementFee(calculateFormDto));
            calculateResultsDto.setTotalPaymentSum(calculatorService.totalPaymentSum(calculateFormDto));
        } catch (IOException exception) {
            logger.error("Error: " + exception);
        }

        return calculateResultsDto;
    }
}
