package com.dao;

import java.util.List;

import com.entity.Reservation;

public interface ReservationDAO {
	public List<Reservation> getReservations();
	public Reservation getReservationByReservationID(int reservationID);
	public List<Reservation> getReservationByLearnerID(int learnerID);
	public List<Reservation> getReservationBySportsType(int sportsType);
	public List<Reservation> getReservationBefore(List<Reservation> reservationSet);
	public List<Reservation> getReservationNow(List<Reservation> reservationSet);
	public List<Reservation> getReservationAfter(List<Reservation> reservationSet);
	public Boolean insertReservation(int reservationID, int learnerID,
			String sportsName, String time,  String place, String introduction, int stateFlag, int maxNum, String price, String phoneNumber, String resvationName);
	public Boolean insertReservation(Reservation reservation);
	public Boolean deleteRreservation(int reservationID);
	public Boolean updateReservation(Reservation reservation);
	
	
	
}
