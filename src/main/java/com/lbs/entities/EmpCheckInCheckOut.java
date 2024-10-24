package com.lbs.entities;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//add so
@Entity
@Table(name="Employee_Details_CheckInOut")
public class EmpCheckInCheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id", nullable = false)  
    private EmployeeData employee;  

    @Column(name="Check_In", nullable = false)
    private ZonedDateTime checkIn;

    @Column(name="Check_Out")
    private ZonedDateTime checkOut;

    @Column(name="Total_Hrs")
    private String totalHrs;

    
    
    public String getTotalHoursWorked() {
        if (checkOut != null) {
            Duration duration = Duration.between(checkIn, checkOut);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            return String.format("%02d hrs %02d min %02d sec", hours, minutes, seconds);
        }
        return "0 hrs 0 min 0 sec";
    }
    
    
    public void setCheckOut(ZonedDateTime checkOut) {
        this.checkOut = checkOut;
        if (checkOut != null) {
            Duration duration = Duration.between(checkIn, checkOut);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            this.totalHrs = String.format("%02d hrs %02d min %02d sec", hours, minutes, seconds);  // Store formatted string
        }
    }

	


	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public EmployeeData getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeData employee) {
		this.employee = employee;
	}

	public ZonedDateTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(ZonedDateTime checkIn) {
		this.checkIn = checkIn;
	}

	public String getTotalHrs() {
		return totalHrs;
	}

	public void setTotalHrs(String totalHrs) {
		this.totalHrs = totalHrs;
	}

	public ZonedDateTime getCheckOut() {
		return checkOut;
	}

	public EmpCheckInCheckOut(long cId, EmployeeData employee, ZonedDateTime checkIn, ZonedDateTime checkOut,
			String totalHrs) {
		super();
		this.cId = cId;
		this.employee = employee;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHrs = totalHrs;
	}

	public EmpCheckInCheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmpCheckInCheckOut [cId=" + cId + ", employee=" + employee + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", totalHrs=" + totalHrs + "]";
	}
    
    
    

    
    
}
