package com.dao;

import java.util.List;

import com.entity.CourseSignUp;

public interface CourseSignUpDAO {
	public List<CourseSignUp> getCourseSignUps();
	public List<CourseSignUp> getCourseSignUpByCourseID(int courseID);
	public List<CourseSignUp> getCourseSignUpByLearnerID(int learnerID);
	public Boolean insertCourseSignUp(int courseID, int learnerID);
	public Boolean insertCourseSignUp(CourseSignUp coursesignup);
	public Boolean deleteCourseSignUp(int courseID, int learnerID);
	public Boolean hasSignedUp(int courseID, int learnerID);

}
