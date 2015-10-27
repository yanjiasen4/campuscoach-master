package com.action;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Course;
import com.manager.CourseManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowUserCoursesAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6753104524976475068L;
	
	private CourseManager courseManager;
	private List<Course> userCourses;
	private List<Course> userHistoryCourses;
	
	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	
	public List<Course> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<Course> userCourses) {
		this.userCourses = userCourses;
	}

	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
        userCourses = courseManager.getUserCourses(learnerID);
        if(userCourses == null || userCourses.isEmpty()){
        	return SUCCESS;
        }
        userHistoryCourses = courseManager.getUserHistoryCourses(learnerID);
        return SUCCESS;
	}
	
	public void appexecute() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
        String learnerID =  request.getParameter("userID");
        userCourses = courseManager.getUserCourses(Integer.parseInt(learnerID));
        int result = 1;
        if(userCourses == null || userCourses.isEmpty()){
        	result = 0;
        }
        // format json result
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        if(result == 1) {
        	jsonObject.put("courses", userCourses);  
        }
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		System.out.println(jsonObject.toString());
		out.println(jsonObject.toString());  
		out.flush();  
		out.close();         
	}

	public List<Course> getUserHistoryCourses() {
		return userHistoryCourses;
	}

	public void setUserHistoryCourses(List<Course> userHistoryCourses) {
		this.userHistoryCourses = userHistoryCourses;
	}

}
