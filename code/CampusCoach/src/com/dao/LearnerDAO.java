package com.dao;

import java.util.List;

import com.entity.Learner;

public interface LearnerDAO {
	
	public List<Learner> getLearners();
	public Learner getLearnerByUsername(String username);
	public Boolean insertLearner(Learner learner);
	public Boolean deleteLearner(String username);
	public Boolean setLearner(Learner learner);
	public Learner getLearnerByLearnerID(int learnerID);
	

}
