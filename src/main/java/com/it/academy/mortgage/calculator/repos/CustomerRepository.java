package com.it.academy.mortgage.calculator.repos;

import com.it.academy.mortgage.calculator.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
