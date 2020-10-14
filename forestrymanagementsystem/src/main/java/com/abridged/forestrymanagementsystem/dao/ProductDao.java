package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Product;


public interface ProductDao {
	public Product getProduct(String productId);

	public boolean addProduct(Product product);

	public boolean updateProduct(Product product);

	public boolean deleteProduct(String productId);
	
	public List<Product> getAllProducts();
}
