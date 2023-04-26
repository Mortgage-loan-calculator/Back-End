package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity(name = "detailed_form")
@AllArgsConstructor
public class DetailedForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String buyOption;
    private boolean studentLoan;
    private boolean otherLoans;
    private int familyMembers;
    private boolean politicallyExposed;

    public DetailedForm() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuyOption() {
        return buyOption;
    }

    public void setBuyOption(String buyOption) {
        this.buyOption = buyOption;
    }

    public boolean isStudentLoan() {
        return studentLoan;
    }

    public void setStudentLoan(boolean studentLoan) {
        this.studentLoan = studentLoan;
    }

    public boolean isOtherLoans() {
        return otherLoans;
    }

    public void setOtherLoans(boolean otherLoans) {
        this.otherLoans = otherLoans;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public boolean isPoliticallyExposed() {
        return politicallyExposed;
    }

    public void setPoliticallyExposed(boolean politicallyExposed) {
        this.politicallyExposed = politicallyExposed;
    }
}
