package com.lbs.controller;

import java.util.HashMap;
import java.util.Map;

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

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Admin admin) {
//        boolean isAuthenticated = adminService.validateAdmin(admin.getUsername(),admin.getPassword());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful! Welcome Admin.");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {

        boolean isAuthenticated = adminService.validateAdmin(admin.getUsername(), admin.getPassword());

        if (isAuthenticated) {

            Map<String, String> response = new HashMap<>();

            response.put("redirectUrl", "/index"); // URL to redirect

            return ResponseEntity.ok(response); // Return the response as a JSON object


        } else {

            return ResponseEntity.status(401).body("Invalid credentials");

        }

    }


}

