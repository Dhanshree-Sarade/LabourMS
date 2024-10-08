package com.lbs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.lbs.entities.Admin;
import com.lbs.services.AdminService;


@RestController
@RequestMapping("/admin")

public class AdminController {
	private AdminService adminService;

    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        boolean isAuthenticated = adminService.validateAdmin(admin.getUsername(),admin.getPassword());
        if (isAuthenticated) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/index"); // assuming /index maps to the index page in your controller
            return ResponseEntity.ok(redirectView);

        } else {
        	return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}

