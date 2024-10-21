package com.lbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.entities.EmployeeData;
import com.lbs.services.EmpCheckService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")

public class EmpCheckController {

    @Autowired
    private EmpCheckService empCheckService;

      
    
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
    
    // Controller method to get records by Employee ID
    @GetMapping("/records/{empId}")
    public ResponseEntity<List<EmpCheckInCheckOut>> getRecordsByEmpId(@PathVariable EmployeeData emp) {
        List<EmpCheckInCheckOut> records = empCheckService.getRecordsByEmpId(emp);
        return ResponseEntity.ok(records);
    }

}
