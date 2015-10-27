package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Coach;
import com.dao.CoachDAO;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;


public class CoachDAOImpl extends BaseHibernateImpl implements CoachDAO{
	
	private int defaultCoachFlag;
	public int getDefaultCoachFlag() {
		return defaultCoachFlag;
	}

	public void setDefaultCoachFlag(int defaultCoachFlag) {
		this.defaultCoachFlag = defaultCoachFlag;
	}

	@SuppressWarnings("unchecked")
	public List<Coach> getAllCoaches() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Coach where stateFlag = :sf";
			Query query = session.createQuery(hql);
			query.setInteger("sf", defaultCoachFlag);
			List<Coach> list = (List<Coach>)query.list();
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
	public Coach getCoachByCoachID(int coachID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Coach where coachID = :cid";
			Query query = session.createQuery(hql);
			query.setInteger("cid", coachID);
			List<Coach> list = (List<Coach>)query.list();
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

	@SuppressWarnings("unchecked")
	public List<Coach> getCoachByRealname(String realName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Coach where realName = :rn";
			Query query = session.createQuery(hql);
			query.setString("rn", realName);
			List<Coach> list = (List<Coach>)query.list();
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
	public List<Coach> getCoachBySportsName(String sportsName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Coach where sportsName = :sn";
			Query query = session.createQuery(hql);
			query.setString("sn", sportsName);
			List<Coach> list = (List<Coach>)query.list();
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

	public Boolean insertCoach(int learnerID, String realName, String password, String sportsName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			Coach coach = new Coach();
			coach.setLearnerID(learnerID);
			coach.setRealname(realName);
			coach.setSportsName(sportsName);
			session.save(coach);
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

	public Boolean deleteCoach(int coachID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from Coach where coachID = :cid";
			Query query = session.createQuery(hql);
			query.setInteger("cid", coachID);
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

	public Boolean updateCoach(Coach coach) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		
		try {
			session.update(coach);
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

	public Boolean insertCoach(Coach coach) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(coach);
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
	public Coach getCoachByLearnerID(int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Coach where learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setInteger("lid", learnerID);
			List<Coach> list = (List<Coach>)query.list();
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
