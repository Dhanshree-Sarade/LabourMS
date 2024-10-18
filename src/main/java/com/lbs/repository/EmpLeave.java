package com.lbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbs.entities.EmployeeData;
import com.lbs.entities.LeaveEmp;

public interface EmpLeave extends JpaRepository<LeaveEmp,Long>{

	List<LeaveEmp> findByEmployeeL_Id(String empId);
	
}
