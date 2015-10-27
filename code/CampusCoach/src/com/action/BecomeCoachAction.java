package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Coach;
import com.manager.CoachManager;
import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BecomeCoachAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492673081025831686L;
	
	private CoachManager coachManager;
	private LearnerManager learnerManager;
	private Coach coach;
	
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
        String username = (String) session.get("user");
        coach.setLearnerID(learnerID);
		if(learnerManager.setLearner2Coach(username)){
			coachManager.insertCoach(coach);
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
		String userName = request.getParameter("userName");
		coach.setLearnerID(Integer.parseInt(learnerID));
		String result = null;
		if(learnerManager.setLearner2Coach(userName)){
			coachManager.insertCoach(coach);
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

	public CoachManager getCoachManager() {
		return coachManager;
	}

	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}
	
	

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public LearnerManager getLearnerManager() {
		return learnerManager;
	}

	public void setLearnerManager(LearnerManager learnerManager) {
		this.learnerManager = learnerManager;
	}

}
