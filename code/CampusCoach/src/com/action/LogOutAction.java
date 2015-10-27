package com.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOutAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8012083580758977811L;
	
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        session.remove("user");
        session.remove("id");
        session.remove("SSOCode");
        session.remove("info");
        session.remove("phoneNumber");
        if(session.containsKey("coachID")){
        	session.remove("coachID");
        	session.remove("coachName");
        }
        return SUCCESS;
	}
	
}
