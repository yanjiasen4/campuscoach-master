package com.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.manager.ReservationManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReceiveAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556288608461410841L;
	
	private ReservationManager reservationManager;
	private int reservationID;
	public ReservationManager getReservationManager() {
		return reservationManager;
	}
	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int coachID = (Integer) session.get("coachID");
        String coachName = (String) session.get("coachName");
        if(reservationManager.receive(reservationID, coachID, coachName)){
        	return SUCCESS;
        }
        else {
        	return ERROR;
        }
	}
	
	public void appexecute() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		int coachID = Integer.parseInt(request.getParameter("coachID"));
		String coachName = request.getParameter("coachName");
		String result = null;
		if(reservationManager.receive(reservationID,  coachID,  coachName)) {
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
	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}


}
