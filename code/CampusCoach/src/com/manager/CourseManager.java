package com.manager;

import java.util.List;


import com.entity.Course;
import com.form.CourseSignUpForm;

public interface CourseManager {
	public List<Course> getAllCourses();
	public Course getCourseByCourseID(int courseID);
	public List<Course> getCourseByCoachID(int coachID);
	public List<Course> getCourseBySportsType(int sportsType);
	public List<Course> getCourseBefore();
	public List<Course> getCourseNow();
	public List<Course> getCourseAfter();
	public Boolean insertCourse(int courseID, String coachID, int sportsType, 
			String time, String place, String introduction, int stateFlag);
	public Boolean deleteCourse(int courseID);
	public Boolean updateCourse(Course course);
	
	//------------------------------------------------------------------------
	public List<Course> getAllNowCourses();
	public List<Course> getUserCourses(int userID);
	public List<CourseSignUpForm> getSignUpList(int courseID);
	public Boolean insertCourse(Course course);
	public List<Course> getUserHistoryCourses(int learnerID);
	public Boolean signUpCourse(int learnerID, int courseID);
	public String getCurrentID();
}
