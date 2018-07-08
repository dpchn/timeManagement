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

@Repository
public class AssignmentDAO {

	public int createAssignment(Assignment assignment) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction transaction = null;
		System.out.println("Hello");
		Serializable id = 0;
		try {
			transaction = session.beginTransaction();
			id = session.save(assignment);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("assignment dao " + e);
		}
		return (int) id;
	}

	
	//List of assignment of employee
	public boolean getListOfAssignmentOfEmployee(int empID, Date sd, Date ed) {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		String sql = "FROM Assignment where EMP_ID=:id and ((START_TIME<=:sd and END_TIME >=:sd) or (START_TIME <=:ed and END_TIME >=:ed))";
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
