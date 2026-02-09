package com.revshop.service;

import java.util.List;

import com.revshop.dao.ProductDAO;
import com.revshop.model.ProductItem;

public class ProductService {

private ProductDAO pdao=new ProductDAO();
	
	//Seller methodologies
	
	//Adding products to the database
	public boolean addProduct(ProductItem products)
	{
		if(products.getPrice()>products.getMrp())
		{
			System.out.println("Price cannot be greater than MRP!");
			return false;
		}
		if(products.getStockQuantity()<0)
		{
			System.out.println("Stock cannot be less than 0!");
			return false;
		}
		return pdao.addProduct(products);
	}
	
	 public List<ProductItem> viewSellerProducts(int sellerId)
	 {
	        return pdao.getProductsBySeller(sellerId);
	 }
	 
	 //Checking the stock value.
	 public void checkLowStock(List<ProductItem> products) 
	 {

	        for (ProductItem p : products) 
	        {
	            if (p.getStockQuantity() <= p.getLowStockThreshold())
	            {
	                System.out.println("LOW STOCK ALERT: " +p.getProductName() +" | Remaining: " + p.getStockQuantity());
	            }
	        }
	 }
	// View all available products
	public List<ProductItem> viewAllProducts() {
	    return pdao.getAllProducts();
	}

	// View products by category
	public List<ProductItem> viewProductsByCategory(int categoryId) {
	    return pdao.getProductsByCategory(categoryId);
	}

	// Search products by keyword
	public List<ProductItem> searchProducts(String keyword) {
	    return pdao.searchProducts(keyword);
	}

}
