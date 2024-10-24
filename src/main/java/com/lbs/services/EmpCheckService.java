  package com.lbs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lbs.entities.EmpCheckInCheckOut;
import com.lbs.entities.EmployeeData;
import com.lbs.repository.EmpCheckRepo;
import com.lbs.repository.EmpolyeeDataRepo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class EmpCheckService {
	


    @Autowired
    private EmpCheckRepo empCheckRepo;

    @Autowired
    private EmpolyeeDataRepo employeeDataRepo;

      
    public EmpCheckInCheckOut checkIn(String  id) {
        EmployeeData employee = employeeDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmpCheckInCheckOut existingCheckIn = empCheckRepo.findByEmployeeAndCheckOutIsNull(employee);
        if (existingCheckIn != null) {
            throw new RuntimeException("Employee is already checked in.");
        }

        EmpCheckInCheckOut checkInRecord = new EmpCheckInCheckOut();
        checkInRecord.setEmployee(employee);
        checkInRecord.setCheckIn(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        return empCheckRepo.save(checkInRecord);
    }


    public EmpCheckInCheckOut checkOut(String id) {
        EmployeeData employee = employeeDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmpCheckInCheckOut checkInRecord = empCheckRepo.findByEmployeeAndCheckOutIsNull(employee);
        if (checkInRecord == null) {
            throw new RuntimeException("Employee hasn't checked in yet or already checked out.");
        }

        checkInRecord.setCheckOut(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        return empCheckRepo.save(checkInRecord);
    }

    public List<EmpCheckInCheckOut> getAllRecords() {
        return empCheckRepo.findAll();
    }
   




	public List<EmpCheckInCheckOut> getRecordsByEmpId(String empId) {
		
		    return empCheckRepo.findByEmployee_Id(empId); 
		
	}


	public double calculateSalary(String id) {
		EmployeeData employee = employeeDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Fetch all attendance records for the employee
        List<EmpCheckInCheckOut> attendanceRecords = empCheckRepo.findByEmployee_Id(id);

        // Calculate total hours worked
        double totalHoursWorked = attendanceRecords.stream()
                .mapToDouble(record -> {
                    if (record.getCheckOut() != null) {
                        Duration duration = Duration.between(record.getCheckIn(), record.getCheckOut());
                        return duration.toHours() + duration.toMinutesPart() / 60.0;
                    }
                    return 0;
                }).sum();

        // Calculate hourly wage
        double hourlyWage = employee.getSalary() / 160;

        // Calculate salary based on total hours worked
        double totalSalaryForWorkedHours = totalHoursWorked * hourlyWage;

        return totalSalaryForWorkedHours;
    

	}
    


    
}
