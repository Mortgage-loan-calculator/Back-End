package com.it.academy.mortgage.calculator.services;

import com.it.academy.mortgage.calculator.models.Admin;
import com.it.academy.mortgage.calculator.repos.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final AdminRepository adminRepository;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminService(AdminRepository adminRepository, EmailValidator emailValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.emailValidator = emailValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addAdmin(Admin newAdmin) {
        boolean isValidEmail = emailValidator.test(newAdmin.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        boolean adminExists = adminRepository.findByUsername(newAdmin.getUsername()).isPresent();
        if (adminExists){
            throw new IllegalStateException("username already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(newAdmin.getPassword());
        newAdmin.setPassword(encodedPassword);
        adminRepository.save(newAdmin);

        // TODO: Send confirmation token
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }
}
