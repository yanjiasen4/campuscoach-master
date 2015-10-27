package com.daoImpl;

import com.entity.ReservationReceive;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.ReservationReceiveDAO;
import com.hibernateTool.BaseHibernateImpl;
import com.hibernateTool.HibernateSessionFactory;

public class ReservationReceiveDAOImpl extends BaseHibernateImpl implements ReservationReceiveDAO{

	@SuppressWarnings("unchecked")
	public List<ReservationReceive> getReservationReceives() {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservationreceive";
			Query query = session.createQuery(hql);
			List<ReservationReceive> list = (List<ReservationReceive>)query.list();
			ts.commit();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ReservationReceive> getReservationReceiveByCoachID(String coachID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservationreceive where coachID = :cid";
			Query query = session.createQuery(hql);
			query.setString("cid", coachID);
			List<ReservationReceive> list = (List<ReservationReceive>)query.list();
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
	public List<ReservationReceive> getReservationReceiveByReservationID(int reservationID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Reservationreceive where reservationID = :rid";
			Query query = session.createQuery(hql);
			query.setInteger("rid", reservationID);
			List<ReservationReceive> list = (List<ReservationReceive>)query.list();
			ts.commit();
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			if(ts != null)
				ts.rollback();
		}
		finally {
			HibernateSessionFactory.closeSession();
		}
		return null;
	}

	public Boolean insertReservationReceive(int reservationID, int coachID, String coachName) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			ReservationReceive reservationreceive = new ReservationReceive();
			reservationreceive.setCoachID(coachID);
			reservationreceive.setReservationID(reservationID);	
			reservationreceive.setCoachName(coachName);
			session.save(reservationreceive);
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

	public Boolean deleteReservationReceive(int reservationID, String coachID) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete from Reservationreceive where reservationID = :rid and coachID = :cid";
			Query query = session.createQuery(hql);
			query.setString("rid", Integer.toString(reservationID));
			query.setString("cid", coachID);
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

	public Boolean insertReservationReceive(ReservationReceive reservationreceive) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(reservationreceive);
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
