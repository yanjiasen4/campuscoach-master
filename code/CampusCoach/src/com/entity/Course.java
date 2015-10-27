package com.entity;

public class Course {
	private int courseID;
	private String coachName;
	private int coachID;
	private String time;
	private String place;
	private String introduction;
	private int stateFlag;
	private int maxNum;
	private String price;
	private int enrollNum;
	private String sportsName;
	private String phoneNumber;
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getEnrollNum() {
		return enrollNum;
	}
	public void setEnrollNum(int enrollNum) {
		this.enrollNum = enrollNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(int stateFlag) {
		this.stateFlag = stateFlag;
	}
	public int getCoachID() {
		return coachID;
	}
	public void setCoachID(int coachID) {
		this.coachID = coachID;
	}
	

}
