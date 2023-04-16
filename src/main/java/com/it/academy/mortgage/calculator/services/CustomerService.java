package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.exceptions.CustomerNotFoundException;
import com.it.academy.mortgage.calculator.models.Customer;
import com.it.academy.mortgage.calculator.models.CustomerRequest;
import com.it.academy.mortgage.calculator.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> fetchAllCustomers() {return customerRepository.findAll();}

    public void addCustomer(CustomerRequest newCustomer) {
        Customer customer = new Customer(newCustomer.name(), newCustomer.number(), newCustomer.email(), "IP address implementation", LocalDateTime.now());
        customerRepository.save(customer);
    }

    public void deleteCustomerById(String id) {customerRepository.deleteById(id);}

    public Customer fetchCustomerByName(String name){
        return customerRepository.findCustomerByName(name);
    };

    public ResponseEntity<Customer> fetchCustomerById(String id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)), HttpStatus.ACCEPTED);
    }
}
