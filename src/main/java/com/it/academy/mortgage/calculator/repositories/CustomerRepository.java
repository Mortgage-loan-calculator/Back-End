package com.it.academy.mortgage.calculator.repositories;

import com.it.academy.mortgage.calculator.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findCustomerByName(String name);

}
