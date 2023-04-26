package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotBlank
    private String name;
    @Column(name = "number")
    @Length(max = 20, message = "Phone number too long")
    private String phoneNumber;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Column(name = "ip_address")
    private String ipAddress;
    private LocalDateTime time;
    private String action;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calculate_form_id", referencedColumnName = "id")
    private CalculateForm calculateForm;
//    @NotNull
//    @Min(value = 0L, message = "familyMembers must be positive")
//    private Integer familyMembers;
//    @NotNull
//    private Boolean haveChildren;
//    @NotNull
//    @Min(value = 0L, message = "homePrice must be positive")
//    private Integer homePrice;
//    @NotNull
//    @Min(value = 0L, message = "loanTerm must be positive")
//    private Integer loanTerm;
//    @NotNull
//    @Min(value = 0L, message = "monthlyFamilyIncome must be positive")
//    private Integer monthlyFamilyIncome;

//    public Customer() {
//        this.time = LocalDateTime.now();
//    }

//    public Customer (LocalDateTime time){
//        this.time = time;
//    }
    public Customer(String name, String phoneNumber, String email, String action, CalculateForm calculateForm) throws UnknownHostException {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.time = LocalDateTime.now(); //pasalint localdate, perkelt i servica
        this.action = action;
        this.calculateForm = calculateForm;
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

    public CalculateForm getCalculateForm() {
        return calculateForm;
    }

    public void setCalculateForm(CalculateForm calculateForm) {
        this.calculateForm = calculateForm;
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
                ", action='" + action + '\'' +
                ", calculateForm=" + calculateForm +
                '}';
    }
}
