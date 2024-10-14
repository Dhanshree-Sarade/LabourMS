  package com.lbs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.entities.EmployeeData;
import com.lbs.repository.EmpCheckRepo;
import com.lbs.repository.EmpolyeeDataRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpCheckService {

    @Autowired
    private EmpCheckRepo empCheckRepo;

    @Autowired
    private EmpolyeeDataRepo employeeDataRepo;

      
    public EmpCheckInCheckOut checkIn(Long id) {
        EmployeeData employee = employeeDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmpCheckInCheckOut existingCheckIn = empCheckRepo.findByEmployeeAndCheckOutIsNull(employee);
        if (existingCheckIn != null) {
            throw new RuntimeException("Employee is already checked in.");
        }

        EmpCheckInCheckOut checkInRecord = new EmpCheckInCheckOut();
        checkInRecord.setEmployee(employee);
        checkInRecord.setCheckIn(LocalDateTime.now());
        return empCheckRepo.save(checkInRecord);
    }

    public EmpCheckInCheckOut checkOut(Long employeeId) {
        EmployeeData employee = employeeDataRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmpCheckInCheckOut checkInRecord = empCheckRepo.findByEmployeeAndCheckOutIsNull(employee);
        if (checkInRecord == null) {
            throw new RuntimeException("Employee hasn't checked in yet or already checked out.");
        }

        checkInRecord.setCheckOut(LocalDateTime.now());
        return empCheckRepo.save(checkInRecord);
    }

    public List<EmpCheckInCheckOut> getAllRecords() {
        return empCheckRepo.findAll();
    }

    
}
