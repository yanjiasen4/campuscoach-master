package com.hibernateTool;

import org.hibernate.Session;


public class BaseHibernateImpl implements BaseHibernate{

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

}
