package com.lbs.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class EmpLeave {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long aId;
	@Column(name="emp_id")
	private long  emp_id;
	@Column(name="Start_Date")
    private LocalDate startDate;
	@Column(name="End_Date")
	private LocalDate endDate;
	@Column(name="Leave_Type")
    private String LeaveType;
	@Column(name="Description")
	private String description;
	@Column(name="Status")
	private String status;
	
	public EmpLeave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpLeave(long aId, long emp_id, LocalDate startDate, LocalDate endDate, String leaveType, String description,
			String status) {
		super();
		this.aId = aId;
		this.emp_id = emp_id;
		this.startDate = startDate;
		this.endDate = endDate;
		LeaveType = leaveType;
		this.description = description;
		this.status = status;
	}

	public long getaId() {
		return aId;
	}

	public void setaId(long aId) {
		this.aId = aId;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmpLeave [aId=" + aId + ", emp_id=" + emp_id + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", LeaveType=" + LeaveType + ", description=" + description + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
	

}
