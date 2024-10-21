package com.lbs.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbs.entities.EmployeeData;
import com.lbs.entities.LeaveEmp;
import com.lbs.services.EmpLeaveService;

@RestController
public class EmpLeaveController {
	@Autowired
	private EmpLeaveService empL;
	
	@PostMapping("/apply")
	public ResponseEntity<LeaveEmp> apply(
	    @RequestParam("id") String id,
	    @RequestParam("currentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
	    @RequestParam("subject") String subject,
	    @RequestParam("LeaveType") String LeaveType,
	    @RequestParam("description") String description,
	    @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	    @RequestParam("status") String status) {
	    
	    // Create a LeaveEmp object
	    LeaveEmp leaveEmp = new LeaveEmp();
	    leaveEmp.setEmployeeL(new EmployeeData(id)); // Assuming EmployeeData takes employeeId as constructor
	    leaveEmp.setCurrentDate(currentDate);
	    leaveEmp.setSubject(subject);
	    leaveEmp.setLeaveType(LeaveType);
	    leaveEmp.setDescription(description);
	    leaveEmp.setStartDate(startDate);
	    leaveEmp.setEndDate(endDate);
	    leaveEmp.setStatus(status);
	    
	    // Call the service method to apply for leave
	    LeaveEmp appliedLeave = empL.apply(leaveEmp);
	    
	    // Return the response with the created leave object
	    return new ResponseEntity<>(appliedLeave, HttpStatus.CREATED);
	}

    
    @GetMapping("/admin/all")
    public ResponseEntity<List<LeaveEmp>> getForAdmin() {
        List<LeaveEmp> leaveList = empL.getAllLeaves();
        return new ResponseEntity<>(leaveList, HttpStatus.OK);
    }
    
    @GetMapping("/leaves/{empId}")
    public ResponseEntity<List<LeaveEmp>> getLeaveRecordsForEmployee(@PathVariable String empId) {
        List<LeaveEmp> leaveList = empL.getLeavesByEmployee(empId);
        return new ResponseEntity<>(leaveList, HttpStatus.OK);
    }
    @PutMapping("/admin/leave/status/{leaveId}")
    public ResponseEntity<LeaveEmp> updateLeaveStatus(
            @PathVariable Long leaveId, 
            @RequestBody Map<String, String> requestBody) { // Accepting a Map for status
        String status = requestBody.get("status"); // Get the status from the JSON body
        LeaveEmp updatedLeave = empL.updateLeaveStatus(leaveId, status);
        if (updatedLeave != null) {
            return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
