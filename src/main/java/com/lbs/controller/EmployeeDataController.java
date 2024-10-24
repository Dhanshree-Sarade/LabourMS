package com.lbs.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<EmployeeData> createEmp(
	        @RequestParam String fName,
	        @RequestParam String lName,
	        @RequestParam String address,
	        @RequestParam String mobile_no,
	        @RequestParam String email,
	        @RequestParam String password,
	        @RequestParam String designation,
	        @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate joining_date,
	        @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate resigning_date,

	        @RequestParam Double salary,
	        
	        @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
	        @RequestParam(required = false) String gender,
	        @RequestParam(required = false) String bloodGroup,
	        @RequestParam(required = false) String maritalStatus,
	        @RequestParam(required = false) String qualification,
	        @RequestParam(required = false) String bankAccountNo,
	        @RequestParam(required = false) String bankName,
	        @RequestParam(required = false) String ifscCode,
	        @RequestParam(required = false) String accountType,
	        @RequestParam("file") MultipartFile file, // Accept file as part of the request
	        @RequestParam(required = false) String status

	) {
	    EmployeeData employeeData = new EmployeeData();
	    // Set all other fields
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
	    employeeData.setResigning_date( resigning_date);    
	    employeeData.setBirthDate(birthDate);
	    employeeData.setGender(gender);
	    employeeData.setBloodGroup(bloodGroup);
	    employeeData.setMaritalStatus(maritalStatus);
	    employeeData.setQualification(qualification);
	    employeeData.setBankAccountNo(bankAccountNo);
	    employeeData.setBankName(bankName);
	    employeeData.setIfscCode(ifscCode);
	    employeeData.setAccountType(accountType);

	    // Store the file
	    String documentName = file.getOriginalFilename();
	    String filePath = "C:\\Users\\Dhanshree\\Documents\\EmployeeImages\\" + documentName; // Update to your actual path

	    // Save the file to the server
	    try {
	        file.transferTo(new File(filePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // Set the path in the employee data
	    employeeData.setDocumentName(filePath); // Store the file path in the database


	    // Save the employee
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
            @RequestParam String id, // Add id parameter
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String address,
            @RequestParam String mobile_no,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String designation,
            @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate joining_date,
            @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate resigning_date,
            @RequestParam Double salary,
            @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String maritalStatus,
            @RequestParam(required = false) String qualification,
            @RequestParam(required = false) String bankAccountNo,
            @RequestParam(required = false) String bankName,
            @RequestParam(required = false) String ifscCode,
            @RequestParam(required = false) String accountType,
            @RequestParam("file") MultipartFile file, // Accept file as part of the request
            @RequestParam(required = false) String status
    ) {
        // Create an EmployeeData object with the incoming parameters
        EmployeeData employeeData = new EmployeeData();

        // Set the ID
        employeeData.setId(id); // Important! Set the ID for the update

        // Set all other fields
        employeeData.setfName(fName);
        employeeData.setlName(lName);
        employeeData.setAddress(address);
        employeeData.setMobile_no(mobile_no);
        employeeData.setEmail(email);
        employeeData.setPassword(password);
        employeeData.setDesignation(designation);
        employeeData.setJoining_date(joining_date);
        employeeData.setSalary(salary);
        employeeData.setResigning_date(resigning_date);
        employeeData.setBirthDate(birthDate);
        employeeData.setGender(gender);
        employeeData.setBloodGroup(bloodGroup);
        employeeData.setMaritalStatus(maritalStatus);
        employeeData.setQualification(qualification);
        employeeData.setBankAccountNo(bankAccountNo);
        employeeData.setBankName(bankName);
        employeeData.setIfscCode(ifscCode);
        employeeData.setAccountType(accountType);

        // Handle file upload
        if (file != null && !file.isEmpty()) {
            String documentName = file.getOriginalFilename();
            employeeData.setDocumentName(documentName);
        }

        // Set the status
        employeeData.setStatus(status);

        // Call the service to update employee data
        EmployeeData updatedEmployee = ser.updateEmpData(employeeData);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }



    
   
    
    
    

    @PostMapping("/login")

	public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {

	    String email = loginDetails.get("email");

	    String password = loginDetails.get("password");

	    boolean isAuthenticated = ser.validateEmployee(email, password);

	    if (isAuthenticated) {

	        EmployeeData employee = ser.findEmployeeByEmail(email);


	        Map<String, Object> response = new HashMap<>();

	        response.put("redirectUrl", "/empIndex");

	        response.put("id", employee.getId());

	        response.put("email", employee.getEmail());

	        return ResponseEntity.ok(response);

	    } else {

	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

	    }

	}





}