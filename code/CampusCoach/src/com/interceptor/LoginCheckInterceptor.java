package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginCheckInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2280232166025302483L;
	
	private String reloginReuslt;
	public String getReloginReuslt() {
		return reloginReuslt;
	}

	public void setReloginReuslt(String reloginReuslt) {
		this.reloginReuslt = reloginReuslt;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if(session.containsKey("user")){
			return invocation.invoke();
		}
		else{
			return Action.LOGIN;
		}
	}

	
}
