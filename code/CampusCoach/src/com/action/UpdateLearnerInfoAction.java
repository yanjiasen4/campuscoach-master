package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateLearnerInfoAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5954960362064450229L;

	private LearnerManager learnerManager;
	
	private String phoneNumber;
	private String realName;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public LearnerManager getLearnerManager() {
		return learnerManager;
	}

	public void setLearnerManager(LearnerManager learnerManager) {
		this.learnerManager = learnerManager;
	}
	
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
		if(learnerManager.updateInfo(learnerID, phoneNumber, realName)){
			session.put("info", 1);
			session.put("phoneNumber", phoneNumber);
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
		String phoneNumber = request.getParameter("phoneNumber");
		String realName = request.getParameter("realName");
		String learnerID = request.getParameter("id");
		String result = null;
		if(learnerManager.updateInfo((Integer.parseInt(learnerID)), phoneNumber, realName)) {
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
