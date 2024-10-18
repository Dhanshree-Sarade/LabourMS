package com.lbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lbs.entities.EmployeeData;

public interface EmpolyeeDataRepo extends JpaRepository<EmployeeData, String>{
	@Query("SELECT MAX(e.id) FROM EmployeeData e")
	String findMaxId();


	EmployeeData findByEmail(String email);
	
	
}
