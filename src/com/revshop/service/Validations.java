package com.revshop.service;

public class Validations {
	    public static boolean isValidEmail(String email) {
	        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	    }

	    public static boolean isValidPassword(String password) {
	        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%!]).{8,}$");
	  }
	    public static boolean isValidNumber(String number) {
	    	return number.matches("^[6-9]\\d{9}$");
	    }
	}

