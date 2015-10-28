package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;





import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.entity.Course;
import com.manager.CourseManager;
import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowCoursesAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1721935953384479478L;
	
	private CourseManager courseManager;
	private LearnerManager learnerManager;
	private List<Course> courses;
	private List<String> sportsList;
	
	public List<String> getSportsList() {
		return sportsList;
	}

	public void setSportsList(List<String> sportsList) {
		this.sportsList = sportsList;
	}

	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public LearnerManager getLearnerManager() {
		return learnerManager;
	}

	public void setLearnerManager(LearnerManager learnerManager) {
		this.learnerManager = learnerManager;
	}
	
	public String execute() {
		courses = courseManager.getAllNowCourses();
		if(courses==null){
			return SUCCESS;
		}
		sportsList = new ArrayList<String>();
		if(!courses.isEmpty()){
			for(Course tmp:courses){
				if(!sportsList.contains(tmp.getSportsName())){
					sportsList.add(tmp.getSportsName());
				}
			}
		}
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        if(session.containsKey("first_login")) {
     
        	int login = (Integer) session.get("first_login");
        	if(login==1) {
        		session.remove("first_login");
        		return "flogin";
        	}
        }
        return SUCCESS;
	}
	
	public void appexecute() throws IOException {
		courses = courseManager.getAllNowCourses();
		JSONArray jsonArray = JSONArray.fromObject(courses);  
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		out.println(jsonArray.toString());  
		out.flush();  
		out.close();
	}

}
