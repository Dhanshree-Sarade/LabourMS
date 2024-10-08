package com.lbs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmpCurd.EmpModel.Employee;
import com.lbs.entities.EmployeeData;
import com.lbs.repository.EmpolyeeDataRepo;


@Service
public class EmployeeDataService {
	@Autowired
	private EmpolyeeDataRepo r;
	
	public EmployeeData createEmp(EmployeeData employeeData) {
		return  r.save(employeeData);
		
	}
	
	

	public List<EmployeeData> ShowAllEmp() {
		// TODO Auto-generated method stub
		return r.findAll();

	}



	public void deleteEmp(Long id) {
		r.deleteById(id);	
	}



	public EmployeeData findId(Long id) {
		return r.findById(id).orElse(null);

	}



	public EmployeeData updateEmpData(EmployeeData employeeData) {
		EmployeeData ne=r.findById(employeeData.getId()).orElse(employeeData);
		if(ne!=null) {
			
			ne.setfName(employeeData.getfName());
			ne.setlName(employeeData.getlName());
			ne.setAddress(employeeData.getAddress());
			ne.setMobile_no(employeeData.getMobile_no());
			ne.setEmail(employeeData.getEmail());
			ne.setPassword(employeeData.getPassword());
			ne.setDesignation(employeeData.getDesignation());
			ne.setJoining_date(employeeData.getJoining_date());
			ne.setSalary(employeeData.getSalary());
			
			
		}
		return r.save(ne);
}


	
	
    

}
