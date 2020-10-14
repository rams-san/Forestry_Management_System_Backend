package com.abridged.forestrymanagementsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.dto.ProductResponse;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.service.ProductService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductRestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private ProductService service;

	/**
	 * This method is use to get product record.
	 * 
	 * @param productId is the parameter to getProductByProductId method.
	 * @return productResponse
	 */
	@GetMapping(path = "/getproduct", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ProductResponse getProductByProductId(String productId) {
		ProductResponse productResponse = new ProductResponse();
		Product info = service.serviceGetProduct(productId, productResponse);
System.out.println(productId);
		if (info != null) {
			productResponse.setError(false);
			productResponse.setMessage("Get the record");
			productResponse.setProduct(info);
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Product id is not present");
			productResponse.setProduct(info);
			throw new NotFoundException();
		}
		return productResponse;
	}// End of getProductByProductId()

	/**
	 * This method is use to add product record.
	 * 
	 * @param product is the parameter to addProduct method.
	 * @return productResponse
	 */
	@PostMapping(path = "/addproduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ProductResponse addProduct(@RequestBody Product product) {
		ProductResponse productResponse = new ProductResponse();
		boolean isAdded = service.serviceAddProduct(product, productResponse);

		if (isAdded) {
			productResponse.setError(false);
			productResponse.setMessage("Product record added successfully");
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Record id not added");
		}
		return productResponse;
	}// End of addProduct()

	/**
	 * This method is use to delete product record.
	 * 
	 * @param productId is the parameter to deleteProductByProductId method.
	 * @return productResponse
	 */
	@DeleteMapping(path = "/deleteproduct", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ProductResponse deleteProductByProductId(String productId) {
		ProductResponse productResponse = new ProductResponse();
		boolean isDeleted = service.serviceDeleteProduct(productId, productResponse);

		if (isDeleted) {
			productResponse.setError(false);
			productResponse.setMessage("Product record deleted successfully");
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Record id not deleted");
		}
		return productResponse;
	}// End of deleteProductByProductId()

	/**
	 * This method is use to update product record.
	 * 
	 * @param product is the parameter to updateProduct method.
	 * @return productResponse
	 */
	@PutMapping(path = "/updateproduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ProductResponse updateProduct(@RequestBody Product product) {
		ProductResponse productResponse = new ProductResponse();
		boolean isUpdated = service.serviceUpdateProduct(product, productResponse);

		if (isUpdated) {
			productResponse.setError(false);
			productResponse.setMessage("Product record updated successfully");
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Record id not updated");
		}
		return productResponse;
	}// End of updateProduct()

	/**
	 * This method is use to get all products record.
	 * 
	 * @return productResponse
	 */
	@GetMapping(path = "/getallproductsrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ProductResponse getAllProductsDetails() {
		List<Product> listRecord = service.serviceGetAllProducts();
		ProductResponse productResponse = new ProductResponse();
		if (listRecord != null) {
			productResponse.setError(false);
			productResponse.setMessage("All Product record");
			productResponse.setProductListInfo(listRecord);
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Product record is not present");
			productResponse.setProductListInfo(listRecord);
		}
		return productResponse;

	}// End of getAllLandsDetails()
}// End of the class
