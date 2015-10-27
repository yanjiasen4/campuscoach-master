package com.daoImpl;

import com.entity.Learner;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.LearnerDAO;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;

public class LearnerDAOImpl extends BaseHibernateImpl implements LearnerDAO{

	@SuppressWarnings("unchecked")
	public List<Learner> getLearners() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Learner";
			Query query = session.createQuery(hql);
			List<Learner> list = (List<Learner>)query.list();
			ts.commit();
			return list;			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}	
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Learner getLearnerByUsername(String username) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Learner where username = :user";
			Query query = session.createQuery(hql);
			query.setString("user", username);
			List<Learner> list = (List<Learner>)query.list();
			ts.commit();
			if(list.size() == 1)
				return list.get(0);
			else
				return null;	
			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	public Boolean deleteLearner(String username) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from Learner where username = :user";
			Query query = session.createQuery(hql);
			query.setString("user", username);
			query.executeUpdate();
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	public Boolean insertLearner(Learner learner) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(learner);
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
		
	}

	public Boolean setLearner(Learner learner) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(learner);
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}		
		return false;	
	}

	@SuppressWarnings("unchecked")
	public Learner getLearnerByLearnerID(int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Learner where learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setInteger("lid", learnerID);
			List<Learner> list = (List<Learner>)query.list();
			ts.commit();
			if(list.size() == 1)
				return list.get(0);
			else
				return null;	
			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

}
