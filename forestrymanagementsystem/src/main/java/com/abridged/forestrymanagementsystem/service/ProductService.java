package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.dto.ProductResponse;

public interface ProductService {
	public Product serviceGetProduct(String productId, ProductResponse productResponse);

	public boolean serviceAddProduct(Product product, ProductResponse productResponse);

	public boolean serviceUpdateProduct(Product product, ProductResponse productResponse);

	public boolean serviceDeleteProduct(String productId, ProductResponse productResponse);
	
	public List<Product> serviceGetAllProducts();
}
