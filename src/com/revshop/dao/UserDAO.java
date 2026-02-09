package com.revshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.revshop.db.DBConnection;

public class UserDAO {

	public int registerUser(String name,String phone,String email,String password,String role,String securityQuestion,String securityAnswer,String passwordHint)
	 {
		 //Inserting data into the users tables
	        String sql = "INSERT INTO users " +"(name,phone, email, password, role, security_question, security_answer, password_hint) " +"VALUES (?,? , ?, ?, ?, ?, ?, ?)";

	        try 
	        {
	            Connection con = DBConnection.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

	            ps.setString(1, name);
	            ps.setString(2, phone);
	            ps.setString(3, email);
	            ps.setString(4, password);
	            ps.setString(5, role);
	            ps.setString(6, securityQuestion);
	            ps.setString(7, securityAnswer);
	            ps.setString(8, passwordHint);

	            int rows = ps.executeUpdate();
	            if (rows > 0) 
	            {
	                     ResultSet rs = ps.getGeneratedKeys();
	                     if (rs.next())
	                     {
	                         return rs.getInt(1);
	                     }
	             }
	            return -1;
	        }
	        catch (Exception Ex) 
	        {
	            Ex.printStackTrace();
	            return -1;
	        }
	   }
	 
	 public int loginUser(String email, String password) 
	 {

		    String sql = "SELECT user_id FROM users WHERE email = ? AND password = ?";

		    try {
		        Connection con = DBConnection.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql);

		        ps.setString(1, email);
		        ps.setString(2, password);
		        
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            return rs.getInt("user_id"); 
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		        return -1;
		}
	 
	 public String getUserRole(int userId) {

		    String role = null;

		    try {
		        String sql = "SELECT role FROM users WHERE user_id = ?";
		        Connection con = DBConnection.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setInt(1, userId);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            role = rs.getString("role");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return role;
		}

	 
	 public int getSellerIdByUserId(int userId) {

		    String sql = "SELECT seller_id FROM sellers WHERE user_id = ?";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, userId);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            return rs.getInt("seller_id");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return -1;
		}
	 
	 
	 public String fetchSecurityQuestion(String email) {

		    String query = "SELECT security_question FROM users WHERE email = ?";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(query)) {

		        ps.setString(1, email);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            return rs.getString("security_question");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}


	 
	 public boolean resetPassword(String email,String securityAnswer,String newPassword) 
	 {

		    String sql = "UPDATE users SET password = ? " +"WHERE email = ? AND security_answer = ?";

		    try {
		        Connection con = DBConnection.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql);

		        ps.setString(1, newPassword);
		        ps.setString(2, email);
		        ps.setString(3, securityAnswer);

		        int rows = ps.executeUpdate();
		        return rows > 0;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	 public void insertSeller(int userId, String businessName, String gstNumber) {

		    String sql = "INSERT INTO sellers (user_id, business_name, gst_number) VALUES (?, ?, ?)";

		    try {
		        Connection con = DBConnection.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql);

		        ps.setInt(1, userId);
		        ps.setString(2, businessName);
		        ps.setString(3, gstNumber);

		        ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}


}

