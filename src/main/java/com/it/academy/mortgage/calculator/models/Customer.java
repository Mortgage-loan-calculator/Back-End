package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotBlank
    private String name;
    @Column(name = "number")
    @Length(min = 1, message = "Phone number too short")
    @Length(max = 20, message = "Phone number too long")
    private String phoneNumber;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Column(name = "ip_address")
    private String ipAddress;
    private final LocalDateTime time;
    private String action;
    @NotNull
    @Min(value = 0L, message = "familyMembers must be positive")
    private Integer familyMembers;
    @NotNull
    private Boolean haveChildren;
    @NotNull
    @Min(value = 0L, message = "homePrice must be positive")
    private Integer homePrice;
    @NotNull
    @Min(value = 0L, message = "loanTerm must be positive")
    private Integer loanTerm;
    @NotNull
    @Min(value = 0L, message = "monthlyFamilyIncome must be positive")
    private Integer monthlyFamilyIncome;

    public Customer() {
        this.time = LocalDateTime.now();
    }

    public Customer(String name, String phoneNumber, String email, String action, Integer familyMembers, Boolean haveChildren, Integer homePrice, Integer loanTerm, Integer monthlyFamilyIncome) throws UnknownHostException {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.time = LocalDateTime.now();
        this.action = action;
        this.familyMembers = familyMembers;
        this.haveChildren = haveChildren;
        this.homePrice = homePrice;
        this.loanTerm = loanTerm;
        this.monthlyFamilyIncome = monthlyFamilyIncome;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Integer familyMembers) {
        this.familyMembers = familyMembers;
    }

    public Boolean getHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(Boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public Integer getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(Integer homePrice) {
        this.homePrice = homePrice;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Integer getMonthlyFamilyIncome() {
        return monthlyFamilyIncome;
    }

    public void setMonthlyFamilyIncome(Integer monthlyFamilyIncome) {
        this.monthlyFamilyIncome = monthlyFamilyIncome;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", time=" + time +
                '}';
    }
}
