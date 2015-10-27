package com.managerImpl;

import com.dao.LearnerDAO;
import com.entity.Learner;
import com.manager.LearnerManager;

public class LearnerManagerImpl implements LearnerManager{
	
	private LearnerDAO learnerDao;

	public LearnerDAO getLearnerDao() {
		return learnerDao;
	}

	public void setLearnerDao(LearnerDAO learnerDao) {
		this.learnerDao = learnerDao;
	}

	public boolean regCheck(String username) {
		if(learnerDao.getLearnerByUsername(username)==null)
		{
			return true;
		}
		else {
			return false;
		}
	}

	public int login(Learner learner) { // 0: learner; 1: coach; 2: not exist; 3: wrong password 4:other error 
		// TODO Auto-generated method stub
		if(learnerDao.getLearnerByUsername(learner.getUsername())==null){
			return 2;
		}
		else {
			Learner dlearner = learnerDao.getLearnerByUsername(learner.getUsername());
			if(dlearner.getPassword().equals(learner.getPassword())){
				if(dlearner.getIsCoach()==0){
					return 0;
				}
				else if(dlearner.getIsCoach()==1){
					return 1;
				}
			}
			else {
				return 3;
			}
		}
		return 4;
	}

	public boolean register(Learner learner) {
		if(regCheck(learner.getUsername())==false){
			return false;
		}
		else {
			learner.setIsCoach(0);
			learnerDao.insertLearner(learner);
			return true;
		}
	}

	public boolean setLearner2Coach(String username) {
		System.out.println("!@");
		Learner learner = learnerDao.getLearnerByUsername(username);
		System.out.println(learner.getLearnerID());
		if(learner.getIsCoach()==1){
			return false;
		}
		else {
			learner.setIsCoach(1);
			learnerDao.setLearner(learner);
			return true;
		}
	}

	public Learner getLearnerByLearnerID(int learnerID) {
		Learner learner = learnerDao.getLearnerByLearnerID(learnerID);
		return learner;
	}

	public Learner getLearnerByUsername(String username) {
		Learner learner = learnerDao.getLearnerByUsername(username);
		return learner;
	}

	public void updateAvatar(int learnerID, String fileFileName) {
		Learner learner = learnerDao.getLearnerByLearnerID(learnerID);
		learner.setAvatar(fileFileName);
		learnerDao.setLearner(learner);
	}

	public boolean updateInfo(int learnerID, String phoneNumber, String realName) {
		Learner learner = learnerDao.getLearnerByLearnerID(learnerID);
		if(learner != null) {
			learner.setPhoneNumber(phoneNumber);
			learner.setRealName(realName);
			learnerDao.setLearner(learner);
			return true;
		}
		else {
			return false;
		}
	}

}
