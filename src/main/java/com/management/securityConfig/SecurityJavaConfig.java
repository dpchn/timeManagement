package com.management.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
	
	// Authentication : Roles define
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("pwd").roles("USER").and()
		.withUser("admin").password("pwd").roles("ADMIN");
	}
	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated()
		.antMatchers("/employee/**").hasRole("USER")
		.antMatchers("/**").hasRole("ADMIN")
		.anyRequest().fullyAuthenticated()
		.and().httpBasic();
		http.csrf().disable();
	}
}