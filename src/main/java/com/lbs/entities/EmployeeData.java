package com.lbs.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EmployeeData")
public class EmployeeData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
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
	private LocalDate joining_date;
	@Column(name="Salary")
    private Double salary;
	@Column(name="Status")
	private String status;
	
	
	public EmployeeData(String status) {
		super();
		this.status = status;
	}

	
	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public EmployeeData(long id, String fName, String lName, String address, String mobile_no, String email,
			String password, String designation, LocalDate joining_date, Double salary) {
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
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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


	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", fName=" + fName + ", lName=" + lName + ", address=" + address
				+ ", mobile_no=" + mobile_no + ", email=" + email + ", password=" + password + ", designation="
				+ designation + ", joining_date=" + joining_date + ", salary=" + salary + ", status=" + status + "]";
	}

		
	

}
