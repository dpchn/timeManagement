package com.management.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="assignment", catalog="employeedb")
public class Assignment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ASSIGNMENT_ID")
	int assignment_id;
	@Column(name="ASSIGNMENT_NAME")
	String assignmentName;
	@Column(name="START_TIME")
	Date startDate;
	@Column(name="END_TIME")
	Date endDate;
	
	//@Column(name="EMP_ID")
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name="EMP_ID")
	Employee employee;

	
	public Assignment(){
		
	}
	public Assignment(String assignmentName, Date startDate, Date endDate, Employee employee) {
		this.assignmentName = assignmentName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
	}

	public int getAssignment_id() {
		return assignment_id;
	}

	public void setAssignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	@Override
	public String toString() {
		return "Time offf created between "+startDate+" to "+endDate;
	}
	
}
