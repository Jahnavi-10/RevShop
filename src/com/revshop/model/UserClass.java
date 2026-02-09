package com.revshop.model;

import java.sql.Timestamp;

public class UserClass {
	
	private int UserId;
	private String name;
	private String contactNumber;
	private String email;
	private String pswrd;
	private String role;
	private String securityQuestion;
	private String ans;
	private String hint;
	private Timestamp CreatedAt;
	
	private UserClass()
	{
		//default constructor
	}

	//Parameterised constructor
	public UserClass( String name, String contactNumber, String email, String pswrd, String role,String securityQuestion, String ans, String hint) 
	{
		
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
		this.pswrd = pswrd;
		this.role = role;
		this.securityQuestion = securityQuestion;
		this.ans = ans;
		this.hint = hint;
		
	}

	//Getters and setters for user class
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswrd() {
		return pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Timestamp getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		CreatedAt = createdAt;
	}
}
