package com.it.academy.mortgage.calculator.dto;

import com.it.academy.mortgage.calculator.models.CalculateResults;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class CalculateFormDto {

    private long id;
    @NotNull(message = "Home price field is mandatory")
    @PositiveOrZero(message = "Home price should be a positive number")
    private Integer homePrice;

    @NotNull(message = "Monthly family income field is mandatory")
    @PositiveOrZero(message = "Monthly family income should be a positive number")
    private Integer monthlyFamilyIncome;

    private int loanTerm;
    private int familyMembers;
    private boolean haveChildren;
    private String city;
    private CalculateResults calculateResults;

    public CalculateFormDto(int homePrice, int loanTerm) {
        this.homePrice = homePrice;
        this.loanTerm = loanTerm;
    }
    public CalculateFormDto() {
    }
}
