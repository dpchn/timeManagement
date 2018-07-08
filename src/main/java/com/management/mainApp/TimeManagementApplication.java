package com.management.mainApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class TimeManagementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TimeManagementApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(TimeManagementApplication.class, args);
	}
}

