package com.lbs.entities;

import java.time.LocalDate;
import java.util.Arrays;
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
import jakarta.persistence.Lob;
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
	
	@Column(name="Birth_Date")
	private LocalDate birthDate;
	@Column(name="gender")
	private String gender;
	@Column(name="Blood_Group")
    private String bloodGroup;
    @Column(name="Marital_Status")
    private String maritalStatus;


	@Column(name="Address")
	private String address;
	@Column(name="Mobile_No")
    private String mobile_no;
	@Column(name="Email")
	private String email;
	@Column(name="Password")
    private String password;
	
	@Column(name="Qualification")
	private String qualification;
	@Column(name="Designation")
    private String designation;
	@Column(name="Joining_Date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate joining_date;
	@Column(name="Resigning_Date")
    private LocalDate resigning_date;
	@Column(name="Salary")
    private Double salary;
    @Column(name="Bank_Account_No")
    private String bankAccountNo;

    @Column(name="Bank_Name")
    private String bankName;

	@Column(name="IFSC_Code")
    private String ifscCode;

    @Column(name="Account_Type")
    private String accountType;
   
    @Column(name="DocumentName")
    private String documentName;

    
    

	@Column(name="Status")
	private String status;
	
	
	@OneToMany(mappedBy = "employee")
	@JsonBackReference
    private List<EmpCheckInCheckOut> checkInCheckOutRecords;
	
	
	@OneToMany(mappedBy = "employeeL")
	@JsonBackReference
	private List<LeaveEmp> leaveRecords;


	


	

	public EmployeeData(String id) {
		this.id=id;
	}







	


	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeData(String id, String fName, String lName, LocalDate birthDate, String gender, String bloodGroup,
			String maritalStatus, String address, String mobile_no, String email, String password, String qualification,
			String designation, LocalDate joining_date, LocalDate resigning_date, Double salary, String bankAccountNo,
			String bankName, String ifscCode, String accountType, String documentName, String status,
			List<EmpCheckInCheckOut> checkInCheckOutRecords, List<LeaveEmp> leaveRecords) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.maritalStatus = maritalStatus;
		this.address = address;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.designation = designation;
		this.joining_date = joining_date;
		this.resigning_date = resigning_date;
		this.salary = salary;
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.accountType = accountType;
		this.documentName = documentName;
		this.status = status;
		this.checkInCheckOutRecords = checkInCheckOutRecords;
		this.leaveRecords = leaveRecords;
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







	public LocalDate getBirthDate() {
		return birthDate;
	}







	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}







	public String getGender() {
		return gender;
	}







	public void setGender(String gender) {
		this.gender = gender;
	}







	public String getBloodGroup() {
		return bloodGroup;
	}







	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}







	public String getMaritalStatus() {
		return maritalStatus;
	}







	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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







	public String getQualification() {
		return qualification;
	}







	public void setQualification(String qualification) {
		this.qualification = qualification;
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







	public LocalDate getResigning_date() {
		return resigning_date;
	}







	public void setResigning_date(LocalDate resigning_date) {
		this.resigning_date = resigning_date;
	}







	public Double getSalary() {
		return salary;
	}







	public void setSalary(Double salary) {
		this.salary = salary;
	}







	public String getBankAccountNo() {
		return bankAccountNo;
	}







	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}







	public String getBankName() {
		return bankName;
	}







	public void setBankName(String bankName) {
		this.bankName = bankName;
	}







	public String getIfscCode() {
		return ifscCode;
	}







	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}







	public String getAccountType() {
		return accountType;
	}







	public void setAccountType(String accountType) {
		this.accountType = accountType;
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











	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}











	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", fName=" + fName + ", lName=" + lName + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", bloodGroup=" + bloodGroup + ", maritalStatus=" + maritalStatus
				+ ", address=" + address + ", mobile_no=" + mobile_no + ", email=" + email + ", password=" + password
				+ ", qualification=" + qualification + ", designation=" + designation + ", joining_date=" + joining_date
				+ ", resigning_date=" + resigning_date + ", salary=" + salary + ", bankAccountNo=" + bankAccountNo
				+ ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", accountType=" + accountType
				+ ", documentName=" + documentName + ", status=" + status + ", checkInCheckOutRecords="
				+ checkInCheckOutRecords + ", leaveRecords=" + leaveRecords + "]";
	}

    








	
	
}