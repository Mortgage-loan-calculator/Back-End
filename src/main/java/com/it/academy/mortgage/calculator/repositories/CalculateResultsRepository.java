package com.it.academy.mortgage.calculator.repositories;

import com.it.academy.mortgage.calculator.models.CalculateResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculateResultsRepository extends JpaRepository<CalculateResults, Long> {
}
