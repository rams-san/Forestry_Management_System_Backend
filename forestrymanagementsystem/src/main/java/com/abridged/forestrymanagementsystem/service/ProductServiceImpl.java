package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.ProductDao;
import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.dto.ProductResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	static ValidationImpl validation = new ValidationImpl();

	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;

	@Override
	public Product serviceGetProduct(String productId, ProductResponse productResponse) {
		if (validation.idValidation(productId)) {
			productResponse.setStatus(200);
			return dao.getProduct(productId);
		} else {
			productResponse.setStatus(401);
			productResponse.setMessage1("Please enter valid product id (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddProduct(Product product, ProductResponse productResponse) {
		if (validation.idValidation(product.getProductId())) {
			flag1 = true;
		} else {
			productResponse.setMessage1("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.nameValidation(product.getProductName())) {
			flag2 = true;
		} else {
			productResponse.setMessage2("Please enter valid product name (e.g suyash)");
		}
		if (validation.quantityValidation(product.getProductQuantity())) {
			flag3 = true;
		} else {
			productResponse.setMessage3("Please enter valid product quantity (e.g range[1-100])");
		}
		if (validation.descriptionValidation(product.getProductDescription())) {
			flag4 = true;
		} else {
			productResponse.setMessage4("Please enter valid product description (e.g product description)");
		}
		if (flag1 && flag2 && flag3 && flag4) {
			productResponse.setStatus(200);
			return dao.addProduct(product);
		} else {
			productResponse.setStatus(401);
			productResponse.setMessage5("please check product properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateProduct(Product product, ProductResponse productResponse) {
		if (validation.idValidation(product.getProductId())) {
			flag1 = true;
		} else {
			productResponse.setMessage1("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.nameValidation(product.getProductName())) {
			flag2 = true;
		} else {
			productResponse.setMessage2("Please enter valid product name (e.g suyash)");
		}
		if (validation.quantityValidation(product.getProductQuantity())) {
			flag3 = true;
		} else {
			productResponse.setMessage3("Please enter valid product quantity (e.g range[1-100])");
		}
		if (validation.descriptionValidation(product.getProductDescription())) {
			flag4 = true;
		} else {
			productResponse.setMessage4("Please enter valid product description (e.g product description)");
		}
		if (flag1 && flag2 && flag3 && flag4) {
			productResponse.setStatus(200);
			return dao.updateProduct(product);
		} else {
			productResponse.setStatus(401);
			productResponse.setMessage5("please check product properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteProduct(String productId, ProductResponse productResponse) {
		if (validation.idValidation(productId)) {
			productResponse.setStatus(200);
			return dao.deleteProduct(productId);
		} else {
			productResponse.setStatus(401);
			productResponse.setMessage1("Please enter valid product id (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Product> serviceGetAllProducts() {
		return dao.getAllProducts();
	}

}
