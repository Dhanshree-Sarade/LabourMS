package com.lbs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lbs.entities.EmployeeData;
import com.lbs.repository.EmpolyeeDataRepo;

import jakarta.transaction.Transactional;


@Service
public class EmployeeDataService {
	@Autowired
	private EmpolyeeDataRepo r;

	private String generateId() {

	    String prefix = "emp";

	    String maxId = r.findMaxId(); // Fetch the max ID from the repository

	    int nextId = 101; // Default value for the first ID



	    if (maxId != null && maxId.length() > prefix.length()) {

	        // Extract the numeric part only if maxId has a valid length

	        String numericPart = maxId.substring(prefix.length());



	        try {

	            nextId = Integer.parseInt(numericPart) + 1; // Increment the numeric part

	        } catch (NumberFormatException e) {

	            // Handle cases where the numeric part is not a valid number

	            System.err.println("Error parsing numeric part of maxId: " + e.getMessage());

	        }
	    }
		return maxId;
	}
	    


    public EmployeeData createEmp(EmployeeData employeeData) {
        if (r.findByEmail(employeeData.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        employeeData.setId(generateId());

        // Set status based on resigning date
        if (employeeData.getResigning_date() == null) {
            employeeData.setStatus("Active");
        } else {
            employeeData.setStatus("Inactive");
        }

        return r.save(employeeData);
    }
	public List<EmployeeData> ShowAllEmp() {
		// TODO Auto-generated method stub
		return r.findAll();

	}



	public void deleteEmp(String id) {
		r.deleteById(id);	
	}



	public EmployeeData findId(String id) {
		return r.findById(id).orElse(null);
	}



	public EmployeeData updateEmpData(EmployeeData employeeData) {
		EmployeeData ne=r.findById(employeeData.getId()).orElse(employeeData);
		
		if (ne == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
	    }

	   
	    EmployeeData employeeWithSameEmail = r.findByEmail(employeeData.getEmail());
	    if (employeeWithSameEmail != null && !employeeWithSameEmail.getId().equals(employeeData.getId())) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
	    }



		if(ne!=null) {
			
			ne.setfName(employeeData.getfName());
			ne.setlName(employeeData.getlName());
			ne.setBirthDate(employeeData.getBirthDate());
			ne.setGender(employeeData.getGender());
			ne.setBloodGroup(employeeData.getBloodGroup());
			ne.setMaritalStatus(employeeData.getMaritalStatus());
			ne.setAddress(employeeData.getAddress());
			ne.setMobile_no(employeeData.getMobile_no());
			ne.setEmail(employeeData.getEmail());
			ne.setPassword(employeeData.getPassword());
			ne.setQualification(employeeData.getDesignation());
			ne.setDesignation(employeeData.getDesignation());
			ne.setJoining_date(employeeData.getJoining_date());
			ne.setResigning_date(employeeData.getResigning_date());
			ne.setSalary(employeeData.getSalary());
			ne.setBankAccountNo(employeeData.getBankAccountNo());
			ne.setBankName(employeeData.getBankName());
			ne.setIfscCode(employeeData.getIfscCode());
			ne.setAccountType(employeeData.getAccountType());
			ne.setDocumentName(employeeData.getDocumentName());
			ne.setStatus(employeeData.getStatus());
			
			////update this 
		}
		ne.setResigning_date(employeeData.getResigning_date());
        if (employeeData.getResigning_date() == null) {
            ne.setStatus("Active");
        } else {
            ne.setStatus("Inactive");
        }

		return r.save(ne);
}
	@Transactional

	public EmployeeData findEmployeeByEmail(String email) {
	    return r.findByEmail(email);
	}
	
	public boolean validateEmployee(String email, String password) {
               EmployeeData employee = r.findByEmail(email);

       
        if (employee != null && employee.getPassword().equals(password)) {
            return true;
        }

       
        return false;
    }

	
	public EmployeeData getEmployeeByEmail(String email) {
	    
	    EmployeeData employee = r.findByEmail(email);

	    	    return employee;
	}
	public EmployeeData findEmployeeById(String empId) {
	    return r.findById(empId).orElse(null);
	}



	

    

}
