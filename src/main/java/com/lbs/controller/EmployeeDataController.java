package com.lbs.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lbs.entities.Admin;
import com.lbs.entities.EmployeeData;
import com.lbs.services.EmployeeDataService;

import jakarta.servlet.http.HttpSession;

@RestController
public class EmployeeDataController {
	
	@Autowired 
	 private EmployeeDataService ser;
	  
	@PostMapping("/employees")
	public ResponseEntity<EmployeeData> createEmp(  @RequestParam String fName,
	        @RequestParam String lName,
	        @RequestParam String address,
	        @RequestParam String mobile_no,
	        @RequestParam String email,
	        @RequestParam String password,
	        @RequestParam String designation,
	        @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate joining_date,
	        @RequestParam Double salary,
	        @RequestParam String status) {
		EmployeeData employeeData = new EmployeeData();
	    employeeData.setfName(fName);
	    employeeData.setlName(lName);
	    employeeData.setAddress(address);
	    employeeData.setMobile_no(mobile_no);
	    employeeData.setEmail(email);
	    employeeData.setPassword(password);
	    employeeData.setDesignation(designation);
	    employeeData.setJoining_date(joining_date);
	    employeeData.setSalary(salary);
	    employeeData.setStatus(status);
		
        EmployeeData createdEmployee = ser.createEmp(employeeData);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
	
	@GetMapping("/employees")
    public ResponseEntity<List<EmployeeData>> showAllEmpData() {
        List<EmployeeData> employees = ser.ShowAllEmp();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	@DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmpData(@PathVariable String id) {
        ser.deleteEmp(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeData> showDetailById(@PathVariable String id) {
        EmployeeData employee = ser.findId(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	


    
    @PutMapping("/employees")
    public ResponseEntity<EmployeeData> updateEmpData(
            @RequestParam("id") String id,
            @RequestParam("fName") String fName,
            @RequestParam("lName") String lName,
            @RequestParam("address") String address,
            @RequestParam("mobile_no") String mobileNo,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("designation") String designation,
            @RequestParam("joining_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate joiningDate,
            @RequestParam("salary") Double salary) {

        // Create an EmployeeData object with the incoming parameters
       EmployeeData employeeData = new EmployeeData();
        employeeData.setId(id);
        employeeData.setfName(fName);
        employeeData.setlName(lName);
        employeeData.setAddress(address);
        employeeData.setMobile_no(mobileNo);
        employeeData.setEmail(email);
        employeeData.setPassword(password);
        employeeData.setDesignation(designation);
        employeeData.setJoining_date(joiningDate);
        employeeData.setSalary(salary);


        

    	EmployeeData updatedEmployee = ser.updateEmpData(employeeData);

    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    	

    }


    
    
    

     
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeData employeeData) {
        boolean isAuthenticated = ser.validateEmployee(employeeData.getEmail(), employeeData.getPassword());
        if (isAuthenticated) {
            // Fetch the employee data from the database
            EmployeeData employee = ser.findEmployeeByEmail(employeeData.getEmail());

            // Prepare the response with employee details
            Map<String, Object> response = new HashMap<>();
            response.put("redirectUrl", "/empIndex"); 
            response.put("id", employee.getId()); 
            response.put("email", employee.getEmail()); 
           
            return ResponseEntity.ok(response);
        }         
    
   
         else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}