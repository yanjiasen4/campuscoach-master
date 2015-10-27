package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.manager.CourseManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4422610638007481866L;
	
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
		//ActionContext actionContext = ActionContext.getContext();
        //Map<String,Object> session = actionContext.getSession();
        //String learnerID = (String) session.get("userid");
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
		//learnerManager.signUpCheck(learnerID);
        if(session.get("info")==null){
        	return "INFO";
        }
        if(courseManager.signUpCourse(learnerID, courseID)){
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
		String learnerID = request.getParameter("userID");
		String result = null;
		if(courseManager.signUpCourse(Integer.parseInt(learnerID),courseID)){
			result = "1";
		}
		else{
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
