package com.managerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;







import com.Message.Message;
import com.dao.CoachDAO;
import com.dao.CourseDAO;
import com.dao.CourseSignUpDAO;
import com.dao.LearnerDAO;
import com.entity.Coach;
import com.entity.Course;
import com.entity.CourseSignUp;
import com.entity.Learner;
import com.form.CourseSignUpForm;
import com.manager.CourseManager;

public class CourseManagerImpl implements CourseManager{
	
	private CourseDAO courseDao;
	private CourseSignUpDAO courseSignUpDao;
	private CoachDAO coachDao;
	private LearnerDAO learnerDao;
	public LearnerDAO getLearnerDao() {
		return learnerDao;
	}

	public void setLearnerDao(LearnerDAO learnerDao) {
		this.learnerDao = learnerDao;
	}

	private int defaultState;

	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	public Course getCourseByCourseID(int courseID) {
		courseDao.getCourseByCourseID(courseID);
		return null;
	}
	
	public CourseSignUpDAO getCourseSignUpDao() {
		return courseSignUpDao;
	}

	public void setCourseSignUpDao(CourseSignUpDAO courseSignUpDao) {
		this.courseSignUpDao = courseSignUpDao;
	}
	

	public List<Course> getCourseByCoachID(String coachID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getCourseBySportsType(int sportsType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getCourseBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getCourseNow() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getCourseAfter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertCourse(int courseID, String coachID, int sportsType,
			String time, String place, String introduction, int stateFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteCourse(int courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	public CourseDAO getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	public List<Course> getAllNowCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = null;
		List<Course> result = null;
		courses = courseDao.getAllCourses();
		result = courseDao.getCourseNow(courses);
		return result;
	}

	public List<Course> getUserCourses(int userID) {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<Course>();
		List<CourseSignUp> userCourses = null;
		userCourses = courseSignUpDao.getCourseSignUpByLearnerID(userID);
		if(userCourses == null || userCourses.isEmpty()){
			return null;
		}
		for(CourseSignUp tmp:userCourses){
			if(courseDao.getCourseByCourseID(tmp.getCourseID())!=null){
				courses.add(courseDao.getCourseByCourseID(tmp.getCourseID()));
			}
		}
		return courses;
	}
	
	public List<Course> getUserHistoryCourses(int userID) {
		List<Course> courses = getUserCourses(userID);
		List<Course> result = courseDao.getCourseAfter(courses);
		return result;
	}
	
	public Boolean insertCourse(Course course) {
		Coach ch = coachDao.getCoachByCoachID(course.getCoachID());
		System.out.println(ch.getRealname());
		course.setCoachName(ch.getRealname());
		System.out.println(course.getCoachName());
		course.setEnrollNum(0); // set enroll number to 0
		course.setStateFlag(defaultState); // all courses are 'now' after been created
		courseDao.insertCourse(course);
		return true;
	}

	public CoachDAO getCoachDao() {
		return coachDao;
	}

	public void setCoachDao(CoachDAO coachDao) {
		this.coachDao = coachDao;
	}

	public Boolean signUpCourse(int learnerID, int courseID) {
		if(courseSignUpDao.hasSignedUp(courseID, learnerID)){
			return false;
		}
		CourseSignUp courseSignUp = new CourseSignUp();
		courseSignUp.setLearnerID(learnerID);
		courseSignUp.setCourseID(courseID);
		
		
		if(courseSignUpDao.insertCourseSignUp(courseSignUp)){
			//发短信通知
			Course cs = courseDao.getCourseByCourseID(courseID);
			System.out.println(courseID);
			System.out.println(cs);
			String chName = cs.getCoachName();
			String text = "【校园教练平台】亲爱的教练"+chName+"，有人报名了您的训练班，快登陆校园教练看看吧！";
			String phone = cs.getPhoneNumber();
			if (phone == null)
				return true;
			try {
				Message.sendSms(text, phone);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}

	public int getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(int defaultState) {
		this.defaultState = defaultState;
	}

	public List<Course> getCourseByCoachID(int coachID) {
		return courseDao.getCourseByCoachID(coachID);
	}

	public String getCurrentID() {
		List<Course> list = courseDao.getAllCourses();
		int currID = -1;
		for(Course tmp:list){
			if(currID < tmp.getCourseID()){
				currID = tmp.getCourseID();
			}
		}
		// TODO Auto-generated method stub
		return Integer.toString(currID);
	}

	public List<CourseSignUpForm> getSignUpList(int courseID) {
		List<CourseSignUp> csList = courseSignUpDao.getCourseSignUpByCourseID(courseID);
		if(csList != null && !csList.isEmpty()){
			List<CourseSignUpForm> list = new ArrayList<CourseSignUpForm>();
			for(CourseSignUp tmp:csList) {
				Learner learner = learnerDao.getLearnerByLearnerID(tmp.getLearnerID());
				CourseSignUpForm signUpForm = new CourseSignUpForm(tmp,learner.getRealName(),
					                                               learner.getPhoneNumber(),learner.getUsername());
				list.add(signUpForm);
			}
			return list;
		}
		else {
			return null;
		}
	}


}
