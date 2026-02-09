package com.revshop.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.List;

	import com.revshop.model.ProductItem;
	import com.revshop.db.DBConnection;
	   

	public class ProductDAO {
		
		//To View ALL Products
		
		public List<ProductItem> getAllProducts() {

		    List<ProductItem> products = new ArrayList<>();

		    String sql = "SELECT * FROM products";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            ProductItem p = new ProductItem();
		            p.setProductId(rs.getInt("product_id"));
		            p.setSellerId(rs.getInt("seller_id"));
		            p.setCategoryId(rs.getInt("category_id"));
		            p.setProductName(rs.getString("product_name"));
		            p.setDescription(rs.getString("description"));
		            p.setPrice(rs.getDouble("price"));
		            p.setMrp(rs.getDouble("mrp"));
		            p.setStockQuantity(rs.getInt("stock_quantity"));
		            p.setLowStockThreshold(rs.getInt("low_stock_threshold"));

		            products.add(p);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return products;
		}

		//View Products by Category
		public List<ProductItem> getProductsByCategory(int categoryId) {

		    List<ProductItem> products = new ArrayList<>();

		    String sql = "SELECT * FROM products WHERE category_id = ?";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, categoryId);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            ProductItem p = new ProductItem();
		            p.setProductId(rs.getInt("product_id"));
		            p.setSellerId(rs.getInt("seller_id"));
		            p.setCategoryId(rs.getInt("category_id"));
		            p.setProductName(rs.getString("product_name"));
		            p.setDescription(rs.getString("description"));
		            p.setPrice(rs.getDouble("price"));
		            p.setMrp(rs.getDouble("mrp"));
		            p.setStockQuantity(rs.getInt("stock_quantity"));

		            products.add(p);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return products;
		}

		//Search Products by Keyword
		
		public List<ProductItem> searchProducts(String keyword) {

		    List<ProductItem> products = new ArrayList<>();

		    String sql = "SELECT * FROM products WHERE product_name LIKE ? OR description LIKE ?";
	

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        String key = "%" + keyword + "%";
		        ps.setString(1, key);
		        ps.setString(2, key);

		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            ProductItem p = new ProductItem();
		            p.setProductId(rs.getInt("product_id"));
		            p.setSellerId(rs.getInt("seller_id"));
		            p.setCategoryId(rs.getInt("category_id"));
		            p.setProductName(rs.getString("product_name"));
		            p.setDescription(rs.getString("description"));
		            p.setPrice(rs.getDouble("price"));
		            p.setMrp(rs.getDouble("mrp"));
		            p.setStockQuantity(rs.getInt("stock_quantity"));

		            products.add(p);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return products;
		}

		
		//Inserting products and its details into the products table
		public boolean addProduct(ProductItem product) {

	        String sql = "INSERT INTO products(seller_id, category_id, product_name, description,price, mrp, stock_quantity, low_stock_threshold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql))
	        {

	            ps.setInt(1, product.getSellerId());
	            ps.setInt(2, product.getCategoryId());              
	            ps.setString(3, product.getProductName());
	            ps.setString(4, product.getDescription());
	            ps.setDouble(5, product.getPrice());
	            ps.setDouble(6, product.getMrp());
	            ps.setInt(7, product.getStockQuantity());
	            ps.setInt(8, product.getLowStockThreshold());

	            return ps.executeUpdate() > 0;

	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	            return false;
	        }
	    }

		 //  Viewing products by seller 
		 public List<ProductItem> getProductsBySeller(int sellerId) 
	    {

	        List<ProductItem> products = new ArrayList<>();

	        String sql = "SELECT * FROM products WHERE seller_id = ?";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql))
	        {

	            ps.setInt(1, sellerId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                ProductItem p = new ProductItem();
	                p.setProductId(rs.getInt("product_id"));
	                p.setSellerId(rs.getInt("seller_id"));
	                p.setCategoryId(rs.getInt("category_id"));
	                p.setProductName(rs.getString("product_name"));
	                p.setDescription(rs.getString("description"));
	                p.setPrice(rs.getDouble("price"));
	                p.setMrp(rs.getDouble("mrp"));
	                p.setStockQuantity(rs.getInt("stock_quantity"));
	                p.setLowStockThreshold(rs.getInt("low_stock_threshold"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));

	                products.add(p);
	            }

	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return products;
	   }
		 public ProductItem getProductById(int productId) {

			    ProductItem product = null;
			    String sql = "SELECT * FROM products WHERE product_id = ?";

			    try {
			        Connection con = DBConnection.getConnection();
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setInt(1, productId);
			        ResultSet rs = ps.executeQuery();

			        if (rs.next()) {
			            product = new ProductItem();
			            product.setProductId(rs.getInt("product_id"));
			            product.setPrice(rs.getDouble("price"));
			            product.setStockQuantity(rs.getInt("stock_quantity"));
			            // set other fields if needed
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return product;
			}

}