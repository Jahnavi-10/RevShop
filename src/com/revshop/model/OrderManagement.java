package com.revshop.model;

import java.sql.Timestamp;

public class OrderManagement {
	
	private int OrderId;
	private int UserId;
	private double totalAmount;
    private String orderStatus;   // PLACED, CANCELLED, DELIVERED
    private Timestamp createdAt;
    
    public OrderManagement()
    {
    	
    }

	public OrderManagement( int userId, double totalAmount, String orderStatus) {
		UserId = userId;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		
	}

	//Getters and Setters
	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
