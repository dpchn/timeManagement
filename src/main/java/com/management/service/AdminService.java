package com.management.service;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.management.dao.AssignmentDAO;
import com.management.dao.EmployeeDAO;
import com.management.model.Assignment;
import com.management.model.Employee;
import com.management.util.Util;

@Service
public class AdminService {

	

	@SuppressWarnings("unchecked")
	public JSONObject createNewAssignment(String assignmentName, String startDate, String endDate, int empId) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		AssignmentDAO assignmentDAO = new AssignmentDAO();
		JSONObject jsonObject = new JSONObject();
		Employee employee = new Employee();
		EmployeeService employeeService = new EmployeeService();
		Date sd = Util.parseDate(startDate);
		Date ed = Util.parseDate(endDate);
		if(sd.after(ed)){
			jsonObject.put("message", "Start date should be less that end date");
			return jsonObject;
		}
		boolean isTimeOffSlotAvailable = employeeService.checkAvailableTimeSlot(empId, sd, ed);
		if (isTimeOffSlotAvailable) {
			employee = employeeDAO.getEmployeeById(empId);
			Assignment assignment = new Assignment(assignmentName, sd, ed, employee);
			int assignemntId = assignmentDAO.createAssignment(assignment);
			jsonObject.put("Message", "Assignment created Successfully");
			HashMap<String, Object> data = new HashMap<>();
			data.put("id", assignemntId);
			data.put("Employee Name", employee.getName());
			data.put("Project Name", assignment.getAssignmentName());
			data.put("Start Date", startDate);
			data.put("End Date", endDate);
			jsonObject.put("data",data);
		} else {
			jsonObject.put("Message", "Assignment overlaps with previous assignments. Please modify your dates.");
		}
		return jsonObject;
	}
}
