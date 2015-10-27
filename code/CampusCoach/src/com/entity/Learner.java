package com.entity;

public class Learner {
	private int learnerID;
	private String username;
	private String password;
	private int isCoach;
	private String avatar;
	private String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	private String realName;

	public int getLearnerID() {
		return learnerID;
	}
	public void setLearnerID(int learnerID) {
		this.learnerID = learnerID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsCoach() {
		return isCoach;
	}
	public void setIsCoach(int isCoach) {
		this.isCoach = isCoach;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
