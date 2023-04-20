package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.models.Customer;

import com.it.academy.mortgage.calculator.models.CustomerRequest;
import com.it.academy.mortgage.calculator.services.CustomerService;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = {"http://localhost:4200", "https://mortgage-loan-calculator-front-end2.onrender.com"}, allowCredentials="true")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Customer> fetchAllCustomers(){
        return customerService.fetchAllCustomers();
    }

    @PostMapping
    public void addCustomer (@RequestBody CustomerRequest customer) throws UnknownHostException {customerService.addCustomer(customer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteStudentById (@PathVariable (name="id") String id){
        customerService.deleteCustomerById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/byname")
    public List<Customer>  fetchCustomersByName(@RequestParam() String name) {
        return customerService.fetchCustomersByName(name);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> fetchCustomerById(@PathVariable String id) {
        return customerService.fetchCustomerById(id);
    }
}