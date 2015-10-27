package com.daoImpl;

import com.entity.Course;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;





import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.CourseDAO;

public class CourseDAOImpl extends BaseHibernateImpl implements CourseDAO{

	@SuppressWarnings("unchecked")
	public List<Course> getAllCourses() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Course";
			Query query = session.createQuery(hql);
			List<Course> list = (List<Course>)query.list();
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
	public Course getCourseByCourseID(int courseID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Course where courseID = :cid";
			Query query = session.createQuery(hql);
			query.setString("cid", Integer.toString(courseID));
			List<Course> list = (List<Course>)query.list();
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
	public List<Course> getCourseByCoachID(int coachID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Course where coachID = :cid";
			Query query = session.createQuery(hql);
			query.setInteger("cid", coachID);
			List<Course> list = (List<Course>)query.list();
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
	public List<Course> getCourseBySportsName(String sportsName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Course where sportsName = :sn";
			Query query = session.createQuery(hql);
			query.setString("sn", sportsName);
			List<Course> list = (List<Course>)query.list();
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

	public List<Course> getCourseBefore(List<Course> courseSet) {
		if(courseSet == null || courseSet.isEmpty()) {
			return null;
		}
		List<Course> list = new ArrayList<Course>();
		for(Course course : courseSet) {
			if(course.getStateFlag() == 1)
				list.add(course);
		}
		return list;
	}

	public List<Course> getCourseNow(List<Course> courseSet) {
		if(courseSet == null || courseSet.isEmpty()) {
			return null;
		}
		List<Course> list = new ArrayList<Course>();
		for(Course course : courseSet) {
			if(course.getStateFlag() == 1)
				list.add(course);
		}
		return list;
	}

	public List<Course> getCourseAfter(List<Course> courseSet) {
		if(courseSet == null || courseSet.isEmpty()) {
			return null;
		}
		List<Course> list = new ArrayList<Course>();
		for(Course course : courseSet) {
			if(course.getStateFlag() == 3)
				list.add(course);
		}
		return list;
	}

	public Boolean insertCourse(int courseID, int coachID, String coachName,
			String sportsName, String time, String place,
			String introduction, int stateFlag, int maxNum, String price, int enrollNum, String phoneNumber) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			Course course = new Course();
			course.setCoachID(coachID);
			course.setCoachName(coachName);
			course.setSportsName(sportsName);
			course.setCourseID(courseID);
			course.setIntroduction(introduction);
			course.setPlace(place);
			course.setStateFlag(stateFlag);
			course.setTime(time);
			course.setMaxNum(maxNum);
			course.setPhoneNumber(phoneNumber);
			course.setPrice(price);
			session.save(course);
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

	public Boolean deleteCourse(int courseID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from Course where courseID = :cid";
			Query query = session.createQuery(hql);
			query.setString("cid", Integer.toString(courseID));
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
	
	public Boolean updateCourse(Course course) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(course);
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

	public Boolean insertCourse(Course course) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(course);
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

	public List<Course> getCourseUnValidate(List<Course> courseSet) {
		if(courseSet == null || courseSet.isEmpty()) {
			return null;
		}
		List<Course> list = new ArrayList<Course>();
		for(Course course : courseSet) {
			if(course.getStateFlag() == 0)
				list.add(course);
		}
		return list;
	}


	
}
