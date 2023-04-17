package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "calculate_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double maxLoan;
    private double totalInterestPaid;
    private double agreementFee;
    private double totalPaymentSum;
    @OneToOne
    @JoinColumn(name = "calculate_form_id", referencedColumnName = "id")
    private CalculateForm calculateForm;
}
