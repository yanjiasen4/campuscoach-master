package com.dao;

import java.util.List;

import com.entity.Course;

public interface CourseDAO {
	public List<Course> getAllCourses();
	public Course getCourseByCourseID(int courseID);
	public List<Course> getCourseByCoachID(int coachID);
	public List<Course> getCourseBySportsName(String sportsName);
	public List<Course> getCourseUnValidate(List<Course> courseSet);
	public List<Course> getCourseBefore(List<Course> courseSet);
	public List<Course> getCourseNow(List<Course> courseSet);
	public List<Course> getCourseAfter(List<Course> courseSet);
	public Boolean insertCourse(int courseID, int coachID, String coachName,
			String sportsName, String time, String place, String introduction, int stateFlag, int maxNum, String price, int enrollNum, String phoneNumber );
	public Boolean insertCourse(Course course);
	public Boolean deleteCourse(int courseID);
	public Boolean updateCourse(Course course);
	
	
}
