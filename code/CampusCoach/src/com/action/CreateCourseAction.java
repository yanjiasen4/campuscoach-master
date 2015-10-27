package com.action;

import java.io.IOException;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Course;
import com.manager.CourseManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CreateCourseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8246910563502144665L;
	
	private CourseManager courseManager;
	private Course course;

	
	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String execute() throws UnsupportedEncodingException {
		System.out.println(course.getSportsName());
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
		int coachID = (Integer) session.get("coachID");
		String coachName = (String) session.get("coachName");
		course.setCoachID(coachID);
		course.setCoachName(coachName);
		if(courseManager.insertCourse(course)) {
			/*
			String pageID = courseManager.getCurrentID();
			String fileDir = "course";
			String postfix = ".jsp";
			String courseUrl = fileDir + '/' + pageID + postfix;
			CopyFileTool copy = new CopyFileTool();
			File newFile = new File("template/courseTmp.jsp");
			File oldFile = new File(courseUrl);
			copy.fileChannelCopy(oldFile, newFile); */
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public void appexecute() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String coachID = request.getParameter("coachID");
		String coachName = request.getParameter("coachName");
		course.setCoachID(Integer.parseInt(coachID));
		course.setCoachName(coachName);
		String result = null;
		if(courseManager.insertCourse(course)) {
			result = "1";
		}
		else {
			result = "0";
		}
		JSONObject json;
		Map<String,String> tmp = new HashMap<String,String>();
		tmp.put("result", result);
		json = JSONObject.fromObject(tmp);
		PrintWriter out = response.getWriter();  
		out.println(json); 
		out.flush();  
		out.close();	
	}

}
