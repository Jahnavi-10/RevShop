package com.revshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.revshop.db.DBConnection;
import com.revshop.model.NotificationClass;

public class NotificationDAO {

	    // ADD NOTIFICATION
	    public void addNotification(int userId, String message) {

	        String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";

	        try {
	        	Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1, userId);
	            ps.setString(2, message);
	            ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // VIEW NOTIFICATIONS
	    public List<NotificationClass> getNotificationsByUser(int userId) {

	        List<NotificationClass> list = new ArrayList<>();

	        String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";

	        try  {
	        	Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                NotificationClass n = new NotificationClass();
	                n.setNotificationId(rs.getInt("notification_id"));
	                n.setUserId(rs.getInt("user_id"));
	                n.setMessage(rs.getString("message"));
	                n.setCreatedAt(rs.getTimestamp("created_at"));

	                list.add(n);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	}
