package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.disqusEncoder.*;
import com.entity.Coach;
import com.entity.Learner;
import com.manager.CoachManager;
import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434128483294080524L;
	
	private Learner learner;
	private LearnerManager learnerManager;
	private CoachManager coachManager;
	
	private String SSOCode;
	private DisqusEncoder disqusEncoder;
	
	public CoachManager getCoachManager() {
		return coachManager;
	}
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}
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
	
	public String execute() throws IOException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		int result = learnerManager.login(learner);
		Learner dlearner = learnerManager.getLearnerByUsername(learner.getUsername());
		String ret = null;
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
		if(result == 0){
	        session.put("user", dlearner.getUsername());
	        session.put("id", dlearner.getLearnerID());
	        session.put("avatar",dlearner.getAvatar());
	        ret = SUCCESS;
			//return SUCCESS;
		}
		else if(result == 1){
	        session.put("user", dlearner.getUsername());
	        session.put("id", dlearner.getLearnerID());
	        session.put("avatar",dlearner.getAvatar());
	        Coach coach = coachManager.getCoachByLearnerID(dlearner.getLearnerID());
	   
	        session.put("coachID", coach.getCoachID());
	        session.put("coachName",coach.getRealname());
	        ret = SUCCESS;
			//return SUCCESS;
		}
		else {
			if(result == 3){
				ret = "error-password";
				//return "error-password";
			}
			else if(result == 2) {
				ret = "user-not-exist";
				//return "user-not-exist";
			}
		}
		
		session.put("first_login", 1);
		System.out.println(session.get("first_login"));
		
		if(dlearner != null && dlearner.getRealName() != null){
			session.put("info", 1);
			session.put("phoneNumber", dlearner.getPhoneNumber());
		}
		
		if(result == 0 || result == 1){
			disqusEncoder = new DisqusEncoder();
			SSOCode = disqusEncoder.encodeSSO(dlearner.getLearnerID(), dlearner.getUsername(), " ", dlearner.getAvatar());
			session.put("SSOCode",SSOCode);
		}
		return ret;
	}
	
	public void appexecute() throws IOException, InvalidKeyException, SignatureException, NoSuchAlgorithmException{
		int result = learnerManager.login(learner);
		Learner dlearner = learnerManager.getLearnerByUsername(learner.getUsername());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		JSONObject json;
		Map<String,String> tmp = new HashMap<String,String>();
		// format json result
		tmp.put("result", ((Integer) result).toString());
		if(result == 0 || result == 1){
			disqusEncoder = new DisqusEncoder();
			SSOCode = disqusEncoder.encodeSSO(dlearner.getLearnerID(), dlearner.getUsername(), " ", dlearner.getAvatar());
			tmp.put("learnerName", dlearner.getUsername());
			tmp.put("learnerID",((Integer)dlearner.getLearnerID()).toString());
			tmp.put("avatar",dlearner.getAvatar());
			tmp.put("SSOCode",SSOCode);
			if(result == 1){
				Coach coach = coachManager.getCoachByLearnerID(dlearner.getLearnerID());
				tmp.put("coachName",coach.getRealname());
				tmp.put("coachID",((Integer)coach.getCoachID()).toString());
			}
		}
		json = JSONObject.fromObject(tmp);
		PrintWriter out = response.getWriter();  
		out.println(json); 
		out.flush();  
		out.close();
	}
	public DisqusEncoder getDisqusEncoder() {
		return disqusEncoder;
	}
	public void setDisqusEncoder(DisqusEncoder disqusEncoder) {
		this.disqusEncoder = disqusEncoder;
	}
	public String getSSOCode() {
		return SSOCode;
	}
	public void setSSOCode(String sSOCode) {
		SSOCode = sSOCode;
	}
}
