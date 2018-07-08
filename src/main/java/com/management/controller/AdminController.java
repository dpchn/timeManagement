package com.management.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.form.AssignmentForm;
import com.management.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "createAssignment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject createTimeOff(@RequestBody AssignmentForm form) {
		System.out.println("ID---------------"+form.getEmpId());
		JSONObject jsonObject = adminService.createNewAssignment(form.getAssignment(), form.getStartTime(),
				form.getEndTime(), form.getEmpId());

		return jsonObject;
	}
}
