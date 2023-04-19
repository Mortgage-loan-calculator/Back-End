package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "calculate_results")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMaxLoan() {
        return maxLoan;
    }

    public void setMaxLoan(double maxLoan) {
        this.maxLoan = maxLoan;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public void setTotalInterestPaid(double totalInterestPaid) {
        this.totalInterestPaid = totalInterestPaid;
    }

    public double getAgreementFee() {
        return agreementFee;
    }

    public void setAgreementFee(double agreementFee) {
        this.agreementFee = agreementFee;
    }

    public double getTotalPaymentSum() {
        return totalPaymentSum;
    }

    public void setTotalPaymentSum(double totalPaymentSum) {
        this.totalPaymentSum = totalPaymentSum;
    }

    public CalculateForm getCalculateForm() {
        return calculateForm;
    }

    public void setCalculateForm(CalculateForm calculateForm) {
        this.calculateForm = calculateForm;
    }
}
