package com.dao;

import java.util.List;

import com.entity.ReservationReceive;

public interface ReservationReceiveDAO {
	public List<ReservationReceive> getReservationReceives();
	public List<ReservationReceive> getReservationReceiveByCoachID(String coachID);
	public List<ReservationReceive> getReservationReceiveByReservationID(int reservationID);
	public Boolean insertReservationReceive(int reservationID, int coachID, String coachName);
	public Boolean insertReservationReceive(ReservationReceive reservationreceive);
	public Boolean deleteReservationReceive(int reservationID, String coachID);
	
	

}
