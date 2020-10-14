package com.abridged.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abridged.springboot.dto.Customer;
import com.abridged.springboot.dto.CustomerResponse;
import com.abridged.springboot.service.CustomerService;

//@Controller
@RestController
public class CustomerRestController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private CustomerService service;

	@GetMapping(path = "/getCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
	public CustomerResponse getCustomerById(int id) {
		Customer info = service.serviceGetCustomer(id);
		CustomerResponse customerResponse = new CustomerResponse();
		
		if(info!=null) {
			customerResponse.setError(false);
			customerResponse.setMessage("Get the record");
			customerResponse.setCustomer(info);
		}else {
			customerResponse.setError(true);
			customerResponse.setMessage("Customer id is not present");
			customerResponse.setCustomer(info);
		}
		return customerResponse;
	}// End of getEmployeeById()

	
	@PostMapping(path = "/addCustomer", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	@ResponseBody
	public CustomerResponse addCustomer(@RequestBody Customer customer) {
		boolean isAdded = service.serviceAddCustomer(customer);
		CustomerResponse customerResponse = new CustomerResponse();

		if (isAdded) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record added successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not added");
		}
		return customerResponse;
	}// End of addEmployee()

	
	@DeleteMapping(path = "/deleteCustomer/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public CustomerResponse deleteCustomerById(@PathVariable(name = "id") int id) {
		boolean isDeleted = service.serviceDeleteCustomer(id);
		CustomerResponse customerResponse = new CustomerResponse();

		if (isDeleted) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record deleted successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not deleted");
		}
		return customerResponse;
	}// End of deleteCustomerById()

	
	@PutMapping(path = "/updateCustomer", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	@ResponseBody
	public CustomerResponse updateCustomer(@RequestBody Customer customer) {
		boolean isUpdated = service.serviceUpdateCustomer(customer);
		CustomerResponse customerResponse = new CustomerResponse();

		if (isUpdated) {
			customerResponse.setError(false);
			customerResponse.setMessage("Customer record updated successfully");
		} else {
			customerResponse.setError(true);
			customerResponse.setMessage("Record id not updated");
		}
		return customerResponse;
	}// End of updateCustomer()

	
	@GetMapping(path = "getAllRecord", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//	@ResponseBody
	public CustomerResponse getAllCustomerDetails() {
		List<Customer> listRecord = service.serviceGetAllCustomers();
		CustomerResponse customerResponse = new CustomerResponse();
		if(listRecord!=null) {
			customerResponse.setError(false);
			customerResponse.setMessage("All Customer record");
			customerResponse.setListInfo(listRecord);
		}else {
			customerResponse.setError(true);
			customerResponse.setMessage("Customer record is not present");
			customerResponse.setListInfo(listRecord);
		}
		return customerResponse;
	}

}// End of the class
