package com.revshop.model;

import java.sql.Timestamp;

public class ProductItem {
	
	private int productId, sellerId, categoryId;
    private String productName;
    private String description;
    private double price;
    private double mrp;
    private int stockQuantity,lowStockThreshold;
    private Timestamp createdAt;
    public ProductItem()
    {
    	//default constructor
    }
    public ProductItem( int sellerId, int categoryId, String productName, String description,
			double price, double mrp, int stockQuantity, int lowStockThreshold ) 
	{
		this.sellerId = sellerId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.mrp = mrp;
		this.stockQuantity = stockQuantity;
		this.lowStockThreshold = lowStockThreshold;
	
	}

    //Getters and setters
	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getSellerId() {
		return sellerId;
	}


	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getMrp() {
		return mrp;
	}


	public void setMrp(double mrp) {
		this.mrp = mrp;
	}


	public int getStockQuantity() {
		return stockQuantity;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}


	public int getLowStockThreshold() {
		return lowStockThreshold;
	}


	public void setLowStockThreshold(int lowStockThreshold) {
		this.lowStockThreshold = lowStockThreshold;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}

