package com.lbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lbs.entities.Admin;
import com.lbs.entities.EmployeeData;
import com.lbs.services.EmployeeDataService;

@RestController
public class EmployeeDataController {
	
	@Autowired 
	 private EmployeeDataService ser;
	  
	@PostMapping("/employees")
    public ResponseEntity<EmployeeData> createEmp(@RequestBody EmployeeData employeeData) {
        EmployeeData createdEmployee = ser.createEmp(employeeData);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
	
	@GetMapping("/employees")
    public ResponseEntity<List<EmployeeData>> showAllEmpData() {
        List<EmployeeData> employees = ser.ShowAllEmp();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	@DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmpData(@PathVariable Long id) {
        ser.deleteEmp(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeData> showDetailById(@PathVariable Long id) {
        EmployeeData employee = ser.findId(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/employees")
    public ResponseEntity<EmployeeData> updateEmpData(@RequestBody EmployeeData employeeData) {
        EmployeeData updatedEmployee = ser.updateEmpData(employeeData);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody EmployeeData employeeData) {
        boolean isAuthenticated = ser.validateEmployee(employeeData.getEmail(), employeeData.getPassword());
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful! Welcome.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



}
