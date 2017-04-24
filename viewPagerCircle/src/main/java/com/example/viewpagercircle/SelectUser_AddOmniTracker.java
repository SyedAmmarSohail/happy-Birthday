package com.example.viewpagercircle;

public class SelectUser_AddOmniTracker {
	
	String User, DOB;
	 
	public SelectUser_AddOmniTracker(String user, String DOB) {
		// TODO Auto-generated constructor stub
		this.User = user;
		this.DOB = DOB;
	}

	public String getUser(){
		return User;
	}
	
	public String getDOB(){
		return DOB;
	}
	
	public void setUser(String user){
		this.User = user;
	}
	
	public void setDOB(String DOB){
		this.DOB = DOB;
	}
}
