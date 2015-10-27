package com.manager;

import java.util.List;

import com.entity.Reservation;

public interface ReservationManager {
	
	public boolean addReservation(Reservation reservation);

	public List<Reservation> getAllReservation();
	
	public List<Reservation> getNowReservation(List<Reservation> reservationSet);
	
	public List<Reservation> getUserReservation(int learnerID);
	
	public boolean hasReceived(int ReservationID);
	
	public boolean receive(int ReservationID, int CoachID, String CoachName);

}
