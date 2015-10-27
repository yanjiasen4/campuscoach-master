package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CoachCheckInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3354931882816537525L;
	
	private String isNotCoach;
	public String getIsNotCoach() {
		return isNotCoach;
	}
	public void setIsNotCoach(String isNotCoach) {
		this.isNotCoach = isNotCoach;
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
		if(session.containsKey("coachID")){
			return invocation.invoke();
		}
		else{
			return 	isNotCoach;
		}
	}

}
