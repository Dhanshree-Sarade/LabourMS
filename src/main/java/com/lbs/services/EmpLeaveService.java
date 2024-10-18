package com.lbs.services;

import com.lbs.entities.LeaveEmp;
import com.lbs.repository.EmpLeave;
import com.lbs.repository.EmpolyeeDataRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lbs.entities.EmployeeData;

@Service
public class EmpLeaveService {
	@Autowired
	private EmpLeave elr;
    @Autowired
    private EmpolyeeDataRepo employeeRepo;


    public LeaveEmp apply(LeaveEmp leaveRequest) {
        EmployeeData employee = employeeRepo.findById(leaveRequest.getEmployeeL().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + leaveRequest.getEmployeeL().getId()));

        
        leaveRequest.setEmployeeL(employee); 
        leaveRequest.setStatus("Pending");
        leaveRequest.setCurrentDate(LocalDate.now());

        return elr.save(leaveRequest); 
    }


	public List<LeaveEmp> getAllLeaves() {
	       return elr.findAll();

	}


	

	public List<LeaveEmp> getLeavesByEmployee(String empId) {
		return elr.findByEmployeeL_Id(empId);
	}
	
	
    
    

}
