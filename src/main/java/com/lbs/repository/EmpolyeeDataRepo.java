package com.lbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.lbs.entities.EmployeeData;

public interface EmpolyeeDataRepo extends JpaRepository<EmployeeData, Long>{

}
