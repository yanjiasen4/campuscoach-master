package com.managerImpl;

import java.util.List;

import com.dao.CoachDAO;
import com.entity.Coach;
import com.manager.CoachManager;

public class CoachManagerImpl implements CoachManager{
	
	private CoachDAO coachDao;

	public CoachDAO getCoachDao() {
		return coachDao;
	}

	public void setCoachDao(CoachDAO coachDao) {
		this.coachDao = coachDao;
	}

	public boolean insertCoach(Coach coach) {
		coach.setStateFlag(0);
		coachDao.insertCoach(coach);
		
		return true;	
	}

	public Coach getCoachByLearnerID(int learnerID) {
		return coachDao.getCoachByLearnerID(learnerID);
	}

	public List<Coach> getAllCoach() {
		return coachDao.getAllCoaches();
	}


}
