package com.form;

import com.entity.CourseSignUp;

// 为数据库表coursesignup扩充属性，方便前端网页使用
public class CourseSignUpForm {
	
	private CourseSignUp courseSignUp;
	private String realName;    //用户真实姓名
	private String phoneNumber; //用户联系方式
	private String username;    //用户账号名称
	
	public CourseSignUpForm(CourseSignUp courseSignUp, String realName, 
			                String phoneNumber, String username) {
		this.courseSignUp = courseSignUp;
		this.realName = realName;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public CourseSignUp getCourseSignUp() {
		return courseSignUp;
	}
	public void setCourseSignUp(CourseSignUp courseSignUp) {
		this.courseSignUp = courseSignUp;
	}
	
}
