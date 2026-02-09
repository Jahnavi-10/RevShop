package com.revshop.service;

import com.revshop.dao.UserDAO;

public class UserActions {

    private UserDAO dao = new UserDAO();

   //Registering the user into the db!
    public int registerUser(String name,String phone,String email,String password,String role,String securityQuestion,String securityAnswer,String passwordHint,String shopName,String gstNumber) 
    {

    	{
    		System.out.println("Register method called");
    	    // Email validation
    	    if (!Validations.isValidEmail(email)) {
    	        System.out.println("Invalid email format");
    	        return -1;
    	    }

    	    // Password validation
    	    if (!Validations.isValidPassword(password)) {
    	        System.out.println("Password must contain uppercase, lowercase, digit, special char and min 8 length");
    	        return -1;
    	    }

    	    // Mobile validation
    	    if (!Validations.isValidNumber(phone)) {
    	        System.out.println("Invalid mobile number");
    	        return -1;
    	    }
        int userId = dao.registerUser(name,phone,email,password,role,securityQuestion,securityAnswer,passwordHint);

        if (userId > 0 && "SELLER".equalsIgnoreCase(role)) 
            dao.insertSeller(userId, shopName, gstNumber);
        return userId;
    	}
    }
     //Login authentication
    public int loginUser(String email, String password) {
    	    if (!Validations.isValidEmail(email)) {
    	        System.out.println("Invalid email format");
    	        return -1;
    	    }

    	    if (password == null || password.isEmpty()) {
    	        System.out.println("Password cannot be empty");
    	        return -1;
    	    }

    	    return dao.loginUser(email, password);
    	}

    
    public String getUserRole(int userId)
    {
    	return dao.getUserRole(userId);
    }
    
    public int getSellerId(int userId) 
    {
        return dao.getSellerIdByUserId(userId);
    }

    //Reset Password
    public String getSecurityQuestion(String email) {
        return dao.fetchSecurityQuestion(email);
    }

    public boolean resetPassword(String email, String securityAnswer, String newPassword) {

        if (!Validations.isValidEmail(email)) {
            System.out.println("Invalid email");
            return false;
        }

        if (!Validations.isValidPassword(newPassword)) {
            System.out.println("New password is weak");
            return false;
        }

        return dao.resetPassword(email, securityAnswer, newPassword);
    }

}
