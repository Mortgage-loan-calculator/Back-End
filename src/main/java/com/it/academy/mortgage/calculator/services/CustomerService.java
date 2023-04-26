package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.dto.CalculateFormDto;
import com.it.academy.mortgage.calculator.exceptions.CustomerNotFoundException;
import com.it.academy.mortgage.calculator.mappers.CalculateMapper;
import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.Customer;
import com.it.academy.mortgage.calculator.models.CustomerRequest;
import com.it.academy.mortgage.calculator.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    private final CalculateMapper calculateMapper;

    public CustomerService(CustomerRepository customerRepository, CalculateMapper calculateMapper) {
        this.customerRepository = customerRepository;
        this.calculateMapper = calculateMapper;
    }

    public List<Customer> fetchAllCustomers() {return customerRepository.findAll();}

    public CustomerRequest addCustomer(CustomerRequest newCustomer) throws UnknownHostException {
        CalculateForm calculateForm = calculateMapper.fromFormDto(newCustomer.calculateFormDto());
        Customer customer = new Customer(
                newCustomer.name(),
                newCustomer.phoneNumber(),
                newCustomer.email(),
                newCustomer.action(),
                calculateForm
        );
        customerRepository.save(customer);
        sendEmailToCustomer(customer);
        CalculateFormDto calculateFormDto = calculateMapper.toFormDto(customer.getCalculateForm());
        return new CustomerRequest(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getAction(),
                calculateMapper.toFormDto(customer.getCalculateForm())
        );
    }

    public void deleteCustomerById(String id) {customerRepository.deleteById(id);}

    public List<Customer> fetchCustomersByName(String name){
        return customerRepository.findAll().stream().filter(customer -> Objects.equals(customer.getName(), name)).collect(Collectors.toList());
    };

    public ResponseEntity<Customer> fetchCustomerById(String id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)), HttpStatus.ACCEPTED);
    }

    private void sendEmailToCustomer(Customer customer){
        emailSenderService.sendEmail(
                customer.getEmail(),
                "Application for mortgage at Team SwEB",
                "We got your information and will get back to you shortly!!! \n "
        );

    }

}
