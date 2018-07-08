package com.management.service;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.management.dao.AssignmentDAO;
import com.management.dao.EmployeeDAO;
import com.management.model.Assignment;
import com.management.model.Employee;

public class AssignmentService {

	
	
	public void createNewAssignment(String assignmentName, Date startDate, Date endDate, int empId){
		EmployeeDAO employeeDAO = new EmployeeDAO();
		AssignmentDAO assignmentDAO = new AssignmentDAO();
		JSONObject jsonObject=new JSONObject();
		Employee employee = new Employee();
		EmployeeService employeeService = new EmployeeService();
		boolean isTimeOffSlotAvailable = employeeService.checkAvailableTimeSlot(empId, startDate, endDate);
		if(isTimeOffSlotAvailable){
			employee = employeeDAO.getEmployeeById(empId);
			Assignment assignment = new Assignment(assignmentName, startDate, endDate, employee);
			int assignemntId = assignmentDAO.createAssignment(assignment);
			jsonObject.put("message", "Assignment created Successfully");
			HashMap<String, Object> data = new HashMap<>();
			data.put("id", assignemntId);
			data.put("Employee Name", employee.getName());
			data.put("Project Name", assignment.getAssignmentName());
			data.put("Start Date", startDate);
			data.put("End Date", endDate);
			jsonObject.put("data", data);
		}else{
			jsonObject.put("message", "Assignment overlaps with previous assignments. Please modify your dates.");
		}	
	}
}
