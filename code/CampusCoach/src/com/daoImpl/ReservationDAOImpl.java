package com.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Reservation;
import com.dao.ReservationDAO;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;

public class ReservationDAOImpl extends BaseHibernateImpl implements ReservationDAO {

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservations() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservation";
			Query query = session.createQuery(hql);
			List<Reservation> list = (List<Reservation>)query.list();
			ts.commit();
			return list;			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Reservation getReservationByReservationID(int reservationID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservation where reservationID = :rid";
			Query query = session.createQuery(hql);
			query.setString("rid", Integer.toString(reservationID));
			List<Reservation> list = (List<Reservation>)query.list();
			ts.commit();
			if(list.size() == 1)
				return list.get(0);
			else
				return null;	
			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByLearnerID(int learnerID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservation where learnerID = :lid";
			Query query = session.createQuery(hql);
			query.setInteger("lid", learnerID);
			List<Reservation> list = (List<Reservation>)query.list();
			ts.commit();
			return list;	
			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationBySportsType(int sportsType) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservation where sportsType = :st";
			Query query = session.createQuery(hql);
			query.setString("st", Integer.toString(sportsType));
			List<Reservation> list = (List<Reservation>)query.list();
			ts.commit();
			return list;	
			
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null){
				ts.rollback();
			}
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	public List<Reservation> getReservationBefore(List<Reservation> reservationSet) {
		List<Reservation> list = new ArrayList<Reservation>();
		for(Reservation reservation : reservationSet) {
			if(reservation.getStateFlag() == 0)
				list.add(reservation);
		}
		return list;
	}

	public List<Reservation> getReservationNow(List<Reservation> reservationSet) {
		List<Reservation> list = new ArrayList<Reservation>();
		for(Reservation reservation : reservationSet) {
			if(reservation.getStateFlag() == 0)
				list.add(reservation);
		}
		return list;
	}

	public List<Reservation> getReservationAfter(List<Reservation> reservationSet) {
		List<Reservation> list = new ArrayList<Reservation>();
		for(Reservation reservation : reservationSet) {
			if(reservation.getStateFlag() == 2)
				list.add(reservation);
		}
		return list;
	}

	public Boolean insertReservation(int reservationID, int learnerID, 
			String sportsName, String time, String place,
			String introduction, int stateFlag, int maxNum, 
			String price, String phoneNumber, String reservationName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			Reservation reservation = new Reservation();
			reservation.setIntroduction(introduction);
			reservation.setLearnerID(learnerID);
			reservation.setPlace(place);
			reservation.setReservationID(reservationID);
			reservation.setStateFlag(stateFlag);
			reservation.setTime(time);
			reservation.setSportsName(sportsName);
			reservation.setMaxNum(maxNum);
			reservation.setPrice(price);
			reservation.setLearnerPhone(phoneNumber);
			reservation.setReservationName(reservationName);
			session.save(reservation);
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
		
	}

	public Boolean deleteRreservation(int reservationID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from Reservation where reservationID = :rid";
			Query query = session.createQuery(hql);
			query.setString("rid", Integer.toString(reservationID));
			query.executeUpdate();
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	public Boolean updateReservation(Reservation reservation) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		
		try {
			session.update(reservation);
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}		
		return false;	
	}

	public Boolean insertReservation(Reservation reservation) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(reservation);
			ts.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
		
	}
	
	
	
	

}
