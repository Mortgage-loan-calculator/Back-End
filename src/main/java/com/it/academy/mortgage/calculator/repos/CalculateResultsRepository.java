package com.it.academy.mortgage.calculator.repos;

import com.it.academy.mortgage.calculator.models.CalculateForm;
import com.it.academy.mortgage.calculator.models.CalculateResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculateResultsRepository extends JpaRepository<CalculateResults, Long> {
}
