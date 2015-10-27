package com.entity;

import java.io.Serializable;

public class ReservationReceive implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int coachID;
	private String coachName;
	private int reservationID;
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public int getCoachID() {
		return coachID;
	}
	public void setCoachID(int coachID) {
		this.coachID = coachID;
	}
	

}
