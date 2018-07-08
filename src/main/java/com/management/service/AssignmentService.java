package com.management.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dao.AssignmentDAO;
import com.management.dao.EmployeeDAO;
import com.management.model.Assignment;
import com.management.model.Employee;

public class AssignmentService {

	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	AssignmentDAO assignmentDAO = new AssignmentDAO();
	public void createNewAssignment(String assignmentName, Date startDate, Date endDate, int empId){
		JSONObject jsonObject=new JSONObject();
		Employee employee = new Employee();
		EmployeeService employeeService = new EmployeeService();
		boolean isTimeOffSlotAvailable = employeeService.checkAvailableTimeSlot(empId, startDate, endDate);
		if(isTimeOffSlotAvailable){
			employee = employeeDAO.getEmployeeById(empId);
			Assignment assignment = new Assignment(assignmentName, startDate, endDate, employee);
			int assignemntId = assignmentDAO.createAssignment(assignment);
			jsonObject.put("Message", "Assignment created Successfully");
			HashMap<String, Object> data = new HashMap<>();
			data.put("id", assignemntId);
			data.put("Employee Name", employee.getName());
			data.put("Project Name", assignment.getAssignmentName());
			data.put("Start Date", startDate);
			data.put("End Date", endDate);
			jsonObject.put("data", data);
		}else{
			jsonObject.put("Message", "Assignment overlaps with previous assignments. Please modify your dates.");
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date startDate = simpleDateFormat.parse("2018-08-15 22:05");
			Date endDate = simpleDateFormat.parse("2018-08-20 22:30");
			new AssignmentService().createNewAssignment("DEMO1", startDate, endDate,1);
		} catch (Exception e) {
			System.out.println("main "+e);
		}
		
	}
}
