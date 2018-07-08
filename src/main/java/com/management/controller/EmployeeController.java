package com.management.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.form.EmployeeForm;
import com.management.form.TimeOffForm;
import com.management.service.EmployeeService;

@SpringBootApplication
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "hello")
	public String sayHello() {
		return "Hello";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject addNewEmploee(@RequestBody EmployeeForm form) {
		JSONObject jsonObject = employeeService.addUser(form.getName(), form.getAddress(), form.getPhone());
		return jsonObject;
	}

	@RequestMapping(value = "timeoff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject createTimeOff(@RequestBody TimeOffForm form) {
		JSONObject jsonObject = employeeService.createTimeOff(form.getReason(), form.getStartTime(), form.getEndTime(),
				form.getEmpId()    );
		return jsonObject;
	}
}
