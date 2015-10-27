package com.daoImpl;

import com.entity.Course;
import com.entity.CourseSignUp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.CourseSignUpDAO;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;

public class CourseSignUpDAOImpl extends BaseHibernateImpl implements CourseSignUpDAO {

	@SuppressWarnings("unchecked")
	public List<CourseSignUp> getCourseSignUps() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from CourseSignUp ";
			Query query = session.createQuery(hql);
			List<CourseSignUp> list = (List<CourseSignUp>)query.list();
			ts.commit();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CourseSignUp> getCourseSignUpByCourseID(int courseID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from CourseSignUp where courseID = :cid";
			Query query = session.createQuery(hql);
			query.setString("cid", Integer.toString(courseID));
			List<CourseSignUp> list = (List<CourseSignUp>)query.list();
			ts.commit();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CourseSignUp> getCourseSignUpByLearnerID(int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from CourseSignUp where learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setInteger("lid", learnerID);
			List<CourseSignUp> list = (List<CourseSignUp>)query.list();
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

	public Boolean insertCourseSignUp(int courseID, int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			CourseSignUp coursesignup = new CourseSignUp();
			coursesignup.setCourseID(courseID);
			coursesignup.setLearnerID(learnerID);
			session.save(coursesignup);
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

	public Boolean deleteCourseSignUp(int courseID, int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from CourseSignUp where courseID = :cid and learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setString("cid", Integer.toString(courseID));
			query.setInteger("lid", learnerID);
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

	public Boolean insertCourseSignUp(CourseSignUp coursesignup) {
		if(hasSignedUp(coursesignup.getCourseID(),coursesignup.getLearnerID())){
			return false;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(coursesignup);
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

	public Boolean hasSignedUp(int courseID, int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			System.out.println("wtf");
			String hql = "from CourseSignUp where courseID = :cid and learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setInteger("cid", courseID);
			query.setInteger("lid", learnerID);
			@SuppressWarnings("unchecked")
			List<Course> list = (List<Course>)query.list();
			System.out.println(list);
			ts.commit();
			if(!list.isEmpty()){
				return true;
			}
			else {
				return false;
			}
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


}
