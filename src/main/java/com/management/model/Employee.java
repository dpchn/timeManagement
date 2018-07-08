package com.management.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee", catalog="employeedb")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(name="NAME")
	String name ;
	@Column(name="ADDRESS")
	String address;
	@Column(name="PHONE")
	String phone;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "employee", cascade=CascadeType.ALL, targetEntity=TimeOff.class)
	private List<TimeOff> timeOffsList = new ArrayList<TimeOff>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "employee", cascade=CascadeType.ALL, targetEntity=Assignment.class)
	private List<Assignment> assginmentList = new ArrayList<Assignment>();

	public Employee(){
		
	}
	
	public Employee(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<TimeOff> getTimeOffsList() {
		return timeOffsList;
	}

	public void setTimeOffsList(List<TimeOff> timeOffsList) {
		this.timeOffsList = timeOffsList;
	}

	public List<Assignment> getAssginmentList() {
		return assginmentList;
	}

	public void setAssginmentList(List<Assignment> assginmentList) {
		this.assginmentList = assginmentList;
	}
	

}
