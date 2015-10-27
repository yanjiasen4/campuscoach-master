package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.entity.Reservation;
import com.manager.ReservationManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegRequireAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9127755972536443941L;
	
	private ReservationManager reservationManager;
	private Reservation reservation;
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public ReservationManager getReservationManager() {
		return reservationManager;
	}

	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        int learnerID = (Integer) session.get("id");
		reservation.setLearnerID(learnerID); // test
		if(reservationManager.addReservation(reservation)){
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
		reservation.setLearnerID(Integer.parseInt(learnerID));
		String result = null;
		if(reservationManager.addReservation(reservation)){
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
