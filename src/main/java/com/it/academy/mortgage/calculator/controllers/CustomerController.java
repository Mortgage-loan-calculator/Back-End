package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.models.Customer;

import com.it.academy.mortgage.calculator.services.CustomerService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @GetMapping
    public List<Customer> fetchAllCustomers(){
        return customerService.fetchAllCustomers();
    }

    @PostMapping
    public void addCustomer (@RequestBody Customer customer){customerService.addCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById (@PathVariable (name="id") String id){
        customerService.deleteCustomerById(id);
    }

    @GetMapping("/byname")
    public List<Customer>  fetchCustomersByName(@RequestParam() String name) {
        return customerService.fetchCustomersByName(name);
    }
    @GetMapping("/{id}")
    public Customer fetchCustomerById(@PathVariable String id) {
        return customerService.fetchCustomerById(id);
    }
}