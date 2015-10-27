package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.entity.Reservation;
import com.manager.ReservationManager;
import com.opensymphony.xwork2.ActionSupport;

public class ShowReservationAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -168071263648698126L;
	
	private ReservationManager reservationManager;
	private List<Reservation> reservations;
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public ReservationManager getReservationManager() {
		return reservationManager;
	}

	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	
	public String execute(){
		reservations = reservationManager.getAllReservation();
		System.out.println(reservations);
		return SUCCESS;
	}
	
	public void appexecute() throws IOException{
		reservations = reservationManager.getAllReservation();
		JSONArray jsonArray = JSONArray.fromObject(reservations);  
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		out.println(jsonArray.toString());  
		out.flush();  
		out.close();
	}

}
