package com.it.academy.mortgage.calculator.controllers;

import com.it.academy.mortgage.calculator.models.Admin;
import com.it.academy.mortgage.calculator.services.AdminService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@PostAuthorize("Admin")
@RestController
@RequestMapping("adminRegistration")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminRegistrationController {
    private final AdminService adminService;

    public AdminRegistrationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public void addAdmin (@RequestBody Admin newAdmin){
        adminService.addAdmin(newAdmin);
    }
}
