package com.lbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.services.EmpCheckService;

import java.util.List;

//@RestController
//@RequestMapping("/api/attendance")
@Controller
public class EmpCheckController {

    @Autowired
    private EmpCheckService empCheckService;

      
    
    @PostMapping("/checkin/{id}")
    public ResponseEntity<EmpCheckInCheckOut> checkIn(@PathVariable("id") Long id) {
        EmpCheckInCheckOut checkInRecord = empCheckService.checkIn(id);
        return ResponseEntity.ok(checkInRecord);
    }


    
    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable("id") Long id) { // Explicitly specify "id"
        EmpCheckInCheckOut checkOutRecord = empCheckService.checkOut(id);
        String totalHours = checkOutRecord.getTotalHoursWorked();
        return ResponseEntity.ok("Employee checked out. Total hours worked: " + totalHours);
    }
    
//  @PostMapping("/checkout/{id}")
//  public ResponseEntity<String> checkOut(@PathVariable Long id) {
//      EmpCheckInCheckOut checkOutRecord = empCheckService.checkOut(id);
//      String totalHours = checkOutRecord.getTotalHoursWorked(); 
//      return ResponseEntity.ok("Employee checked out. Total hours worked: " + totalHours);
//  }

    @GetMapping("/records")
    public ResponseEntity<List<EmpCheckInCheckOut>> getAllRecords() {
        List<EmpCheckInCheckOut> records = empCheckService.getAllRecords();
        return ResponseEntity.ok(records);
    }
    
    // Controller method to get records by Employee ID
    @GetMapping("/records/{empId}")
    public ResponseEntity<List<EmpCheckInCheckOut>> getRecordsByEmpId(@PathVariable Long empId) {
        List<EmpCheckInCheckOut> records = empCheckService.getRecordsByEmpId(empId);
        return ResponseEntity.ok(records);
    }

}
