package com.manager;

import java.util.List;

import com.entity.Coach;

public interface CoachManager {
	
	public boolean insertCoach(Coach coach);

	public Coach getCoachByLearnerID(int learnerID);

	public List<Coach> getAllCoach();

}
