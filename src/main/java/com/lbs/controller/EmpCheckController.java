package com.lbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.entities.EmployeeData;
import com.lbs.services.EmpCheckService;
import com.lbs.services.EmployeeDataService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")

public class EmpCheckController {

    @Autowired
    private EmpCheckService empCheckService;
    @Autowired 
	 private EmployeeDataService ser;

      
    
    @PostMapping("/checkin/{id}")
    public ResponseEntity<EmpCheckInCheckOut> checkIn(@PathVariable String id) {
    
        EmpCheckInCheckOut checkInRecord = empCheckService.checkIn(id);
        return ResponseEntity.ok(checkInRecord);
    }


    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable String id) {
        EmpCheckInCheckOut checkOutRecord = empCheckService.checkOut(id);
        String totalHours = checkOutRecord.getTotalHoursWorked();

        return ResponseEntity.ok("Employee checked out. Total hours worked: " + totalHours);
    }
    

    @GetMapping("/records")
    public ResponseEntity<List<EmpCheckInCheckOut>> getAllRecords() {
        List<EmpCheckInCheckOut> records = empCheckService.getAllRecords();
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/records/{id}")
    public ResponseEntity<List<EmpCheckInCheckOut>> getRecordsByEmpId(@PathVariable String id) {
        EmployeeData emp = ser.findEmployeeById(id); // Fetch the employee using ID
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Handle case when employee is not found
        }
        List<EmpCheckInCheckOut> records = empCheckService.getRecordsByEmpId(id); // Pass the empId
        return ResponseEntity.ok(records);
    }
    @GetMapping("/calculate-salary/{id}")
    public ResponseEntity<String> calculateSalary(@PathVariable String id) {
        double totalSalary = empCheckService.calculateSalary(id);
        return ResponseEntity.ok("Total salary for worked hours: " + String.format("%.2f", totalSalary));
    }
  
   

    
}
