package com.lbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbs.entities.LeaveEmp;
import com.lbs.services.EmpLeaveService;

@RestController
public class EmpLeaveController {
	@Autowired
	private EmpLeaveService empL;
	
	
	@PostMapping("/apply")
    public ResponseEntity<LeaveEmp>Apply(@RequestBody LeaveEmp le){
		LeaveEmp applyLeave=empL.apply(le);
		return new ResponseEntity<>(applyLeave,HttpStatus.CREATED);

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
    @PutMapping("/admin/leave/status")
    public ResponseEntity<LeaveEmp> updateLeaveStatus(
            @PathVariable Long leaveId, 
            @RequestParam String status) {
        
        LeaveEmp updatedLeave = empL.updateLeaveStatus(leaveId, status);
        if (updatedLeave != null) {
            return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
