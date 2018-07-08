package com.management.service;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.management.dao.AssignmentDAO;
import com.management.dao.EmployeeDAO;
import com.management.model.Employee;
import com.management.model.TimeOff;
import com.management.util.Util;

@Service
public class EmployeeService {

	EmployeeDAO employeeDAO = new EmployeeDAO();
	AssignmentDAO assignmentDAO = new AssignmentDAO();

	@SuppressWarnings("unchecked")
	public JSONObject addUser(String name, String address, String phone) {
		JSONObject jsonObject = new JSONObject();
		Employee employee = new Employee(name, address, phone);
		int id = employeeDAO.saveEmployee(employee);
		jsonObject.put("id", id);
		jsonObject.put("name", employee.getName());
		jsonObject.put("address", employee.getAddress());
		jsonObject.put("phone", employee.getPhone());

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public JSONObject createTimeOff(String reason, String startTime, String endTime, int empId) {
		JSONObject jsonObject = new JSONObject();
		Date sd = Util.parseDate(startTime);
		Date ed = Util.parseDate(endTime);
		if(sd.after(ed)){
			jsonObject.put("message", "Start date should be less that end date");
			return jsonObject;
		}
		boolean isTimeOffSlotAvailable = checkAvailableTimeSlot(empId, sd, ed);
		if (isTimeOffSlotAvailable) {
			Employee employee = employeeDAO.getEmployeeById(empId);
			TimeOff timeOff = new TimeOff(reason, sd, ed, employee);
			int id = employeeDAO.createTimeOff(timeOff);
			jsonObject.put("Message", "Time Off scheduled Successfully");
			HashMap<String, Object> data = new HashMap<>();
			data.put("id", id);
			data.put("Reason", timeOff.getReason());
			data.put("Start Date", startTime);
			data.put("End Date", endTime);
			jsonObject.put("data", data);
		} else {
			jsonObject.put("message", "Cannot create time off. You already have an assignment during this period.");
		}
		return jsonObject;
	}

	public boolean checkAvailableTimeSlot(int empId, Date startTime, Date endTime) {
		boolean isTimeSlotAvailableInAssignMentTable = assignmentDAO.getListOfAssignmentOfEmployee(empId, startTime,
				endTime);
		if (!isTimeSlotAvailableInAssignMentTable)
			return false;
		boolean isTimeSlotAvailableInTimeOffTable = employeeDAO.checkTimeSlotInTimeOff(empId, startTime, endTime);
		if (!isTimeSlotAvailableInTimeOffTable)
			return false;
		return true;
	}
}
