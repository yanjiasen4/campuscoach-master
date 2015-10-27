package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Reservation;
import com.entity.ReservationReceive;
import com.manager.ReservationManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowUserReservationAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 721502819439008336L;
	
	private ReservationManager reservationManager;
	private List<Reservation> userReservation;
	private List<ReservationReceive> userReservationReceive;
	
	public ReservationManager getReservationManager() {
		return reservationManager;
	}
	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	public List<Reservation> getUserReservation() {
		return userReservation;
	}
	public void setUserReservation(List<Reservation> userReservation) {
		this.userReservation = userReservation;
	}
	public List<ReservationReceive> getUserReservationReceive() {
		return userReservationReceive;
	}
	public void setUserReservationReceive(
			List<ReservationReceive> userReservationReceive) {
		this.userReservationReceive = userReservationReceive;
	}
	
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
		userReservation = reservationManager.getUserReservation(learnerID);
		if(userReservation == null || userReservation.isEmpty()) {
			session.put("emptyRes", 1);
		}
		return SUCCESS;
	}
	
	public void appexecute() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
        String learnerID =  request.getParameter("userID");
        userReservation = reservationManager.getUserReservation(Integer.parseInt(learnerID));
        int result = 1;
        if(userReservation == null || userReservation.isEmpty()){
        	result = 0;
        }
        // format json result
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        if(result == 1) {
        	jsonObject.put("courses", userReservation);
        }
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		System.out.println(jsonObject.toString());
		out.println(jsonObject.toString());  
		out.flush();  
		out.close();
	}
	
}
