package com.action;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.entity.Course;
import com.form.CourseSignUpForm;
import com.manager.CourseManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowCoachCoursesAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5382287882419681382L;
	private CourseManager courseManager;
	private List<Course> coachCourses;
	private Map<Course,List<CourseSignUpForm>> signUpList;
	
	public Map<Course, List<CourseSignUpForm>> getSignUpList() {
		return signUpList;
	}

	public void setSignUpList(Map<Course, List<CourseSignUpForm>> signUpList) {
		this.signUpList = signUpList;
	}

	public List<Course> getCoachCourses() {
		return coachCourses;
	}

	public void setCoachCourses(List<Course> coachCourses) {
		this.coachCourses = coachCourses;
	}

	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int coachID = (Integer) session.get("coachID");
		coachCourses = courseManager.getCourseByCoachID(coachID);
		if(coachCourses != null && !coachCourses.isEmpty()){
			signUpList = new HashMap<Course,List<CourseSignUpForm>>();
			for(Course tmp:coachCourses) {
				signUpList.put(tmp, courseManager.getSignUpList(tmp.getCourseID()));
			}
		}
		System.out.println(signUpList);
		return SUCCESS;
	}



}
