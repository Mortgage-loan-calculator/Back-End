package com.it.academy.mortgage.calculator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    public Customer() {
        this.time = LocalDateTime.now();
    }

    public Customer(String name, String phoneNumber, String email, String action) throws UnknownHostException {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.time = LocalDateTime.now();
        this.action = action;
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
