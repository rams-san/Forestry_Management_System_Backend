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
import com.abridged.forestrymanagementsystem.dto.Customer;
import com.abridged.forestrymanagementsystem.dto.CustomerResponse;
import com.abridged.forestrymanagementsystem.service.CustomerService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerRestController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private CustomerService service;

	/**
	 * This method is use to login as customer.
	 * 
	 * @param customerName and customerPassword are the parameters to loginCustomer
	 *                     method.
	 * @return customerResponse
	 */
	@GetMapping(path = "/logincustomer", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse loginCustomer(String customerName,String customerPassword) {
		CustomerResponse customerResponse = new CustomerResponse();
System.out.println(customerName+" + "+customerPassword);
		boolean login = service.serviceLoginCustomer(customerName, customerPassword, customerResponse);
		if (login) {
			customerResponse.setStatus(200);
			customerResponse.setMessage("Login successful");
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage(customerResponse.getMessage());
			customerResponse.setMessage1(customerResponse.getMessage1());
			customerResponse.setMessage2(customerResponse.getMessage2());
		}
		return customerResponse;
	}// End of loginCustomer()

	/**
	 * This method is use to get customer record.
	 * 
	 * @param customerId is the parameter to getCustomerById method.
	 * @return customerResponse
	 */
	@GetMapping(path = "/getcustomer", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse getCustomerById(String customerId) {
		CustomerResponse customerResponse = new CustomerResponse();
		Customer info = service.serviceGetCustomer(customerId, customerResponse);

		if (info != null) {
			customerResponse.setError(false);
			customerResponse.setMessage("Get the record");
			customerResponse.setCustomer(info);
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Customer id is not present");
			customerResponse.setCustomer(info);
		}
		return customerResponse;
	}// End of getEmployeeById()

	/**
	 * This method is use to add customer record.
	 * 
	 * @param customer is the parameter to addCustomer method.
	 * @return customerResponse
	 */
	@PostMapping(path = "/addcustomer", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse addCustomer(@RequestBody Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		boolean isAdded = service.serviceAddCustomer(customer, customerResponse);

		if (isAdded) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record added successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not added");
		}
		return customerResponse;
	}// End of addEmployee()

	/**
	 * This method is use to delete customer record.
	 * 
	 * @param customerId is the parameter to deleteCustomerById method.
	 * @return customerResponse
	 */
	@DeleteMapping(path = "/deletecustomer", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse deleteCustomerById(String customerId) {
		CustomerResponse customerResponse = new CustomerResponse();
		boolean isDeleted = service.serviceDeleteCustomer(customerId, customerResponse);

		if (isDeleted) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record deleted successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not deleted");
		}
		return customerResponse;
	}// End of deleteCustomerById()

	/**
	 * This method is use to update customer record.
	 * 
	 * @param customerId is the parameter to updateCustomer method.
	 * @return customerResponse
	 */
	@PutMapping(path = "/updatecustomer", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse updateCustomer(@RequestBody Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		boolean isUpdated = service.serviceUpdateCustomer(customer, customerResponse);

		if (isUpdated) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record updated successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not updated");
		}
		return customerResponse;
	}// End of updateCustomer()

	/**
	 * This method is use to get all customers record.
	 * 
	 * @return customerResponse
	 */
	@GetMapping(path = "/getallcustomersrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse getAllCustomerDetails() {
		List<Customer> listRecord = service.serviceGetAllCustomers();
		CustomerResponse customerResponse = new CustomerResponse();
		if (listRecord != null) {
			customerResponse.setError(false);
			customerResponse.setMessage("All Customer record");
			customerResponse.setCustomerListInfo(listRecord);
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Customer record is not present");
			customerResponse.setCustomerListInfo(listRecord);
		}
		return customerResponse;
	}// End of getAllCustomerDetails

}// End of the class
