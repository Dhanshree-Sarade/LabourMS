package com.lbs.entities;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_table")


public class LeaveEmp {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long aId;
		@ManyToOne
		@JsonBackReference
	    @JoinColumn(name = "id")
	    private EmployeeData employeeL;
		@Column(name="`current_date`")
		private LocalDate currentDate;
		@Column(name="Subject")
		private String subject;
	    @Column(name="Leave_Type")
	    private String LeaveType;
		@Column(name="Description")
		private String description;
		@Column(name="Start_Date")
	    private LocalDate startDate;
		@Column(name="End_Date")
		private LocalDate endDate;
		@Column(name="`status`")
		private String status;
		
		public LeaveEmp() {
			super();
			// TODO Auto-generated constructor stub
		}

		public LeaveEmp(long aId, EmployeeData employeeL, LocalDate currentDate, String subject, String leaveType,
				String description, LocalDate startDate, LocalDate endDate, String status) {
			super();
			this.aId = aId;
			this.employeeL = employeeL;
			this.currentDate = currentDate;
			this.subject = subject;
			LeaveType = leaveType;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
		}

		public long getaId() {
			return aId;
		}

		public void setaId(long aId) {
			this.aId = aId;
		}

		public EmployeeData getEmployeeL() {
			return employeeL;
		}

		public void setEmployeeL(EmployeeData employeeL) {
			this.employeeL = employeeL;
		}

		public LocalDate getCurrentDate() {
			return currentDate;
		}

		public void setCurrentDate(LocalDate currentDate) {
			this.currentDate = currentDate;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "EmpLeave [aId=" + aId + ", employeeL=" + employeeL + ", currentDate=" + currentDate + ", subject="
					+ subject + ", LeaveType=" + LeaveType + ", description=" + description + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", status=" + status + "]";
		}
		
		
		
		
	


}
