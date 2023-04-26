package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "calculate_form")
@AllArgsConstructor
@NoArgsConstructor
public class CalculateForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int homePrice;
    private int monthlyFamilyIncome;
    private int loanTerm;
    private int familyMembers;
    private boolean haveChildren;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailed_form_id", referencedColumnName = "id")
    private DetailedForm detailedForm; // TODO

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calculate_form_id", referencedColumnName = "id")
    private CalculateResults calculateResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(int homePrice) {
        this.homePrice = homePrice;
    }

    public int getMonthlyFamilyIncome() {
        return monthlyFamilyIncome;
    }

    public void setMonthlyFamilyIncome(int monthlyFamilyIncome) {
        this.monthlyFamilyIncome = monthlyFamilyIncome;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public DetailedForm getDetailedForm() {
        return detailedForm;
    }

    public void setDetailedForm(DetailedForm detailedForm) {
        this.detailedForm = detailedForm;
    }

    public CalculateResults getCalculateResults() {
        return calculateResults;
    }

    public void setCalculateResults(CalculateResults calculateResults) {
        this.calculateResults = calculateResults;
    }
}
