package com.entity;
import java.io.Serializable;
public class CourseSignUp implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private int courseID;
	private int learnerID;
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getLearnerID() {
		return learnerID;
	}
	public void setLearnerID(int learnerID) {
		this.learnerID = learnerID;
	}

}
