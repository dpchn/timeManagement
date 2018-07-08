package com.management.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.management.dbresource.SessionFactoryHelper;
import com.management.model.Assignment;
import com.management.model.Employee;
import com.management.model.TimeOff;

@Repository
public class EmployeeDAO {

	public int saveEmployee(Employee employee) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction transaction = null;
		Serializable id = 0;
		try {
			transaction = session.beginTransaction();
			id = session.save(employee);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception is emp dao " + e);
		} finally {
			session.close();
		}
		return (int) id;
	}

	//Get employee by id
	public Employee getEmployeeById(int id) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Employee employee = session.get(Employee.class, id);
		session.close();
		return employee;
	}

	public int createTimeOff(TimeOff timeOff) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction transaction = null;
		Serializable id = 0;
		try {
			transaction = session.beginTransaction();
			id = session.save(timeOff);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return (int) id;
	}

	//Check time available time in timeoff table
	public boolean checkTimeSlotInTimeOff(int empID, Date sd, Date ed) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		String sql = "FROM TimeOff where EMP_ID=:id and ((START_TIME<=:sd and END_TIME >=:sd) or (START_TIME <=:ed and END_TIME >=:ed))";
		try {
			Query query = session.createQuery(sql);
			query.setParameter("id", empID);
			query.setParameter("sd", sd);
			query.setParameter("ed", ed);
			List<Assignment> employeeList = query.getResultList();
			return employeeList.size() == 0;
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}

	}

}
