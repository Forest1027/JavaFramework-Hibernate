package com.forest.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration config;
	private static SessionFactory sFactory;
	static{
		config=new Configuration().configure();
		sFactory=config.buildSessionFactory();
	}	
	public static Session openSession() {
		return sFactory.openSession();
	}
}
