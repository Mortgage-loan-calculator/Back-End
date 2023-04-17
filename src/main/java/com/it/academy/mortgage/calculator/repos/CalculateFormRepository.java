package com.it.academy.mortgage.calculator.repos;

import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculateFormRepository extends JpaRepository<CalculateForm, Long> {
}
