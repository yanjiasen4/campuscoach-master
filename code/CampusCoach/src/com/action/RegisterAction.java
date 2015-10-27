package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Learner;
import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4750587017910410574L;
	
	private Learner learner;
	private LearnerManager learnerManager;
	public Learner getLearner() {
		return learner;
	}
	public void setLearner(Learner learner) {
		this.learner = learner;
	}
	public LearnerManager getLearnerManager() {
		return learnerManager;
	}
	public void setLearnerManager(LearnerManager learnerManager) {
		this.learnerManager = learnerManager;
	}
	
	public String execute() throws IOException {
		if(learnerManager.register(learner)){
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public void appexecute() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		JSONObject json;
		Map<String,String> tmp = new HashMap<String,String>();
		if(learnerManager.register(learner)){
			tmp.put("result", "1");
		}
		else {
			tmp.put("result", "0");
		}
		json = JSONObject.fromObject(tmp);
		PrintWriter out = response.getWriter();  
		out.println(json); 
		out.flush();  
		out.close();
	}
}
