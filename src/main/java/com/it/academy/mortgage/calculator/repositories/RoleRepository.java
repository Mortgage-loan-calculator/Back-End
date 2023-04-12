package com.it.academy.mortgage.calculator.repositories;

import com.it.academy.mortgage.calculator.models.ERole;
import com.it.academy.mortgage.calculator.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
