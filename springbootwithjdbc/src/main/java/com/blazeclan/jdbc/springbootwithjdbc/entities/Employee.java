package com.blazeclan.jdbc.springbootwithjdbc.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * entity class or bean class used to define storage class for storing the data.
 */
public class Employee {
	
	private int id;
	private String firstname;
	private String lastname;
	private long phone;
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getWork_exp() {
		return work_exp;
	}
	public void setWork_exp(String work_exp) {
		this.work_exp = work_exp;
	}
	public String getReporting_to() {
		return reporting_to;
	}
	public void setReporting_to(String reporting_to) {
		this.reporting_to = reporting_to;
	}
	public Date getJoinning_date() {
		return joinning_date;
	}
	public void setJoinning_date(Date joinning_date) {
		this.joinning_date = joinning_date;
	}
	
	/*
	 * For new table entry
	 */
	
	private String address;
	private String email_id;
	private  String designation;
    private  String email;
    private  String dept;
    private  String work_exp;
    private  String reporting_to;
    @JsonFormat(pattern="yyyy-MM-dd")
    private  Date joinning_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
}
