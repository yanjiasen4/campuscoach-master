package com.action;

import java.util.Map;

import com.manager.CourseManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DropCourseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7553973521876003971L;
	
	private CourseManager courseManager;
	private int courseID;

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	
	
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
        if(courseManager.deleteCourseSignup(learnerID, courseID)){
        	return SUCCESS;
        } else {
        	return ERROR;
        }
	}

}
