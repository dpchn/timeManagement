package com.management.dbresource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionUtil {

	public static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();
	public static SessionFactory sessionFactory;
	private static HibernateSessionUtil factoryUtil;
	private static Session session;
	static {
		sessionFactory = new Configuration().configure("com/management/dbresource/hibernate.cfg.xml").buildSessionFactory();
	}
	private HibernateSessionUtil(){
		
	}
	
	public static HibernateSessionUtil getSessionFactory(){
		if(factoryUtil==null)
			factoryUtil = new HibernateSessionUtil();
		return factoryUtil;
	}
	

	
	public static Session openSession() {
		session = null;
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	public static void closeSession(){
		session.close();
	}
	
}
