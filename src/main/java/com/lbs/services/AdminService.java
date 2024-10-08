package com.lbs.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lbs.entities.Admin;
import com.lbs.repository.AdminRepository;

@Service
public class AdminService {
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public boolean validateAdmin(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        }
        return false;
    }
}

	

