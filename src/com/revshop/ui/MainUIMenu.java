package com.revshop.ui;

import java.util.Scanner;

import com.revshop.service.UserActions;

public class MainUIMenu 
{ 
	private static Scanner sc = new Scanner(System.in);
	private static UserActions service = new UserActions();
	
	    //Home Page Menu
	    public static void main(String args[])
	    {       System.out.println("\n=== REVSHOP MENU ===");
	            System.out.println("1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Forgot Password");
	            System.out.println("4. Exit");
	            System.out.print("Choose option: ");
	            int choice = Integer.parseInt(sc.nextLine());
	            switch (choice) {
	                case 1:
	                    register();
	                    break;
	                case 2:
	                    login();
	                    break;
	                case 3:
	                    forgotPassword();
	                    break;
	                case 4:
	                    System.out.println("Thank you for using RevShop!");
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice!");
	            }
	    }

	   //Register menu
	    private static void register() {

	        System.out.print("Enter Name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter Phone: ");
	        String phone = sc.nextLine();

    	    // Mobile validation
    	    if (!Validations.isValidNumber(phone)) {
    	        System.out.println("Invalid mobile number");
    	        return -1;
    	    }

	        System.out.print("Enter Email: ");
	        String email = sc.nextLine();
			// Email validation
    	    if (!Validations.isValidEmail(email)) {
    	        System.out.println("Invalid email format");
    	        return -1;
    	    }

	        System.out.print("Enter Password: ");
	        String password = sc.nextLine();
			 // Password validation
    	    if (!Validations.isValidPassword(password)) {
    	        System.out.println("Password must contain uppercase, lowercase, digit, special char and min 8 length");
    	        return -1;
    	    }

	        System.out.print("Confirm Password: ");
	        if (!password.equals(sc.nextLine())) {
	            System.out.println("Passwords do not match!");
	            return;
	        }
	        System.out.print("Enter Role (BUYER/SELLER): ");
	        String role = sc.nextLine().toUpperCase();

	        System.out.print("Security Question: ");
	        String secQ = sc.nextLine();

	        System.out.print("Security Answer: ");
	        String secA = sc.nextLine();

	        System.out.print("Password Hint: ");
	        String hint = sc.nextLine();

	        String shopName = null;
	        String gstNumber = null;

	        if ("SELLER".equals(role)) 
	        {
	            System.out.print("Shop Name: ");
	            shopName = sc.nextLine();

	            System.out.print("GST Number: ");
	            gstNumber = sc.nextLine();
	        }

	        int userId = service.registerUser(name, phone, email, password, role,secQ, secA, hint,shopName, gstNumber );

	        if (userId > 0)
	            System.out.println("Registration successful! User ID: " + userId);
	        else
	            System.out.println("Registration failed!");
	    }

	    //Login Method
	    private static void login() 
	    {

	        System.out.print("Enter Email: ");
	        String email = sc.nextLine();

	        System.out.print("Enter Password: ");
	        String password = sc.nextLine();

	        int user_id= service.loginUser(email, password);

	        if (user_id>0)
	        {
	            System.out.println("Login successful!");
	            String role=service.getUserRole(user_id);
	            if ("SELLER".equals(role)){
	            	int sellerId = service.getSellerId(user_id);
	            	SellerUIMenu.showMenu(sellerId);
	            	}
	            if ("BUYER".equals(role)) 
	                BuyerUIMenu.showMenu(user_id);
	     
	        } 
	        else 
	         System.out.println("Invalid email or password!");
	    }
	    //Forgot Password
	    private static void forgotPassword() {

	        System.out.print("Enter Email: ");
	        String email = sc.nextLine();
	        String question = service.getSecurityQuestion(email);
	        if (question == null) {
	            System.out.println("Email not found!");
	            return;
	        }

	        System.out.println("Security Question: " + question);

	        System.out.print("Enter Security Answer: ");
	        String answer = sc.nextLine();

	        System.out.print("Enter New Password: ");
	        String newPassword = sc.nextLine();

	        boolean success = service.resetPassword(email, answer, newPassword);
	        if (success)
	            System.out.println("Password reset successful!");
	        else
	            System.out.println("Invalid details!");
	    }
}
