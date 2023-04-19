package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.exceptions.CustomerNotFoundException;
import com.it.academy.mortgage.calculator.models.Customer;
import com.it.academy.mortgage.calculator.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> fetchAllCustomers() {return customerRepository.findAll();}

    public void addCustomer(Customer newCustomer) {
        System.out.println(newCustomer );
        customerRepository.save(newCustomer);}

    public void deleteCustomerById(String id) {customerRepository.deleteById(id);}

    public List<Customer> fetchCustomersByName(String name){
        return customerRepository.findAll().stream().filter(customer -> Objects.equals(customer.getName(), name)).collect(Collectors.toList());
    };

    public ResponseEntity<Customer> fetchCustomerById(String id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)), HttpStatus.ACCEPTED);
    }
}
