package com.lbs.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="EmployeeData")

public class EmployeeData {
	@Id
	private String id;
	@Column(name="First_Name")
	private String fName;
	@Column(name="Last_Name")
	private String lName;
	@Column(name="Address")
	private String address;
	@Column(name="Mobile_No")
    private String mobile_no;
	@Column(name="Email")
	private String email;
	@Column(name="Password")
    private String password;
	@Column(name="Designation")
    private String designation;
	@Column(name="Joining_Date")
	@JsonFormat(pattern = "yyyy-MM-dd")

	private LocalDate joining_date;
	@Column(name="Salary")
    private Double salary;
	@Column(name="Status")
	private String status;
	
	
	@OneToMany(mappedBy = "employee")
	@JsonBackReference
    private List<EmpCheckInCheckOut> checkInCheckOutRecords;
	
	
	@OneToMany(mappedBy = "employeeL")
	@JsonBackReference
	private List<LeaveEmp> leaveRecords;


	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeData(String id, String fName, String lName, String address, String mobile_no, String email,
			String password, String designation, LocalDate joining_date, Double salary, String status,
			List<EmpCheckInCheckOut> checkInCheckOutRecords, List<LeaveEmp> leaveRecords) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
		this.designation = designation;
		this.joining_date = joining_date;
		this.salary = salary;
		this.status = status;
		this.checkInCheckOutRecords = checkInCheckOutRecords;
		this.leaveRecords = leaveRecords;
	}


	


	public EmployeeData(String id) {
		this.id=id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile_no() {
		return mobile_no;
	}


	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public LocalDate getJoining_date() {
		return joining_date;
	}


	public void setJoining_date(LocalDate joining_date) {
		this.joining_date = joining_date;
	}


	public Double getSalary() {
		return salary;
	}


	public void setSalary(Double salary) {
		this.salary = salary;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<EmpCheckInCheckOut> getCheckInCheckOutRecords() {
		return checkInCheckOutRecords;
	}


	public void setCheckInCheckOutRecords(List<EmpCheckInCheckOut> checkInCheckOutRecords) {
		this.checkInCheckOutRecords = checkInCheckOutRecords;
	}


	public List<LeaveEmp> getLeaveRecords() {
		return leaveRecords;
	}


	public void setLeaveRecords(List<LeaveEmp> leaveRecords) {
		this.leaveRecords = leaveRecords;
	}


	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", fName=" + fName + ", lName=" + lName + ", address=" + address
				+ ", mobile_no=" + mobile_no + ", email=" + email + ", password=" + password + ", designation="
				+ designation + ", joining_date=" + joining_date + ", salary=" + salary + ", status=" + status
				+ ", checkInCheckOutRecords=" + checkInCheckOutRecords + ", leaveRecords=" + leaveRecords + "]";
	}
    

}