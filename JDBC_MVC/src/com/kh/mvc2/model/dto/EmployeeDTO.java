package com.kh.mvc2.model.dto;

import java.sql.Date;

public class EmployeeDTO {
	
	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phoneNo;
	private String deptCode;
	private String jobCode;
	private String salLev;
	private int salary;
	private int bonus;
	private String managerId;
	private Date hireDate;
	private Date entDate;
	private String entYN;
	
	public EmployeeDTO() {
		super();
	}
	
	public EmployeeDTO(String empId, String empName, String empNo, String email, String phoneNo, String deptCode,
			String jobCode, String salLev, int salary, int bonus, String managerId, Date hireDate, Date entDate,
			String entYN) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phoneNo = phoneNo;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLev = salLev;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.entYN = entYN;
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSalLev() {
		return salLev;
	}
	public void setSalLev(String salLev) {
		this.salLev = salLev;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getEntDate() {
		return entDate;
	}
	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}
	public String getEntYN() {
		return entYN;
	}
	public void setEntYN(String entYN) {
		this.entYN = entYN;
	}
	
	
}
