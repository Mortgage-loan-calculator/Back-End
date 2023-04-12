package com.it.academy.mortgage.calculator.repos;

import com.it.academy.mortgage.calculator.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @org.springframework.data.jdbc.repository.query.Query(value = "SELECT * FROM Customer c WHERE c.name= :name")
    List<Customer> getByName(@Param("name") String name);
}
