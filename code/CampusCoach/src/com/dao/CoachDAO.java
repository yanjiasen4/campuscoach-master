package com.dao;

import java.util.List;

import com.entity.Coach;

public interface CoachDAO {
	public List<Coach> getAllCoaches();
	public Coach getCoachByCoachID(int coachID);
	public List<Coach> getCoachByRealname(String realName);
	public List<Coach> getCoachBySportsName(String sportsName);
	public Boolean insertCoach(int learnerID, String realName, String password, String sportsName);
	public Boolean insertCoach(Coach coach);
	public Boolean deleteCoach(int Coach);
	public Boolean updateCoach(Coach coach);
	public Coach getCoachByLearnerID(int learnerID);
}
