package com.lbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.entities.EmployeeData;

public interface EmpCheckRepo extends JpaRepository<EmpCheckInCheckOut, Long> {
    EmpCheckInCheckOut findByEmployeeAndCheckOutIsNull(EmployeeData employee);
    
    List<EmpCheckInCheckOut> findByEmployeeId(Long empId);
}
