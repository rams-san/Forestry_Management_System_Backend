package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.CustomerDao;
import com.abridged.forestrymanagementsystem.dto.Customer;
import com.abridged.forestrymanagementsystem.dto.CustomerResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;

	static ValidationImpl validation = new ValidationImpl();
	
	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	boolean flag6 = false;
	boolean flag7 = false;
	boolean flag8 = false;

	@Override
	public boolean serviceLoginCustomer(String customerName, String customerPassword, CustomerResponse customerResponse) {
		String validName = null;
		String validPassword = null;
		boolean flag1 = false;
		boolean flag2 = false;
		if (validation.nameValidation(customerName)) {
			validName = customerName;
			flag1 = true;
		} else {
			customerResponse.setMessage("Please enter valid customer name (e.g suyash)");
		}
		if (validation.passwordValidation(customerPassword)) {
			validPassword = customerPassword;
			flag2 = true;
		} else {
			customerResponse.setMessage1("Please enter valid customer password (e.g Sa*@#$%!11)");
		}
		if (flag1 && flag2) {
			customerResponse.setStatus(200);
			return dao.loginCustomer(validName, validPassword);
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage2("Please check customer name and password");
		}
		return false;
	}

	@Override
	public Customer serviceGetCustomer(String customerId, CustomerResponse customerResponse) {
		if (validation.idValidation(customerId)) {
			customerResponse.setStatus(200);
			return dao.getCustomer(customerId);
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage1("Please enter valid customer id (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddCustomer(Customer customer, CustomerResponse customerResponse) {
		
		if (validation.idValidation(customer.getCustomerId())) {
			flag1 = true;
		} else {
			customerResponse.setMessage1("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.nameValidation(customer.getCustomerName())) {
			flag2 = true;
		} else {
			customerResponse.setMessage2("Please enter valid customer name (e.g suyash)");
		}
		if (validation.passwordValidation(customer.getCustomerPassword())) {
			flag3 = true;
		} else {
			customerResponse.setMessage3("Please enter valid customer password (e.g Sa*@#$%!11)");
		}
		if (validation.emailValidation(customer.getCustomerEmail())) {
			flag4 = true;
		} else {
			customerResponse.setMessage4("Please enter valid customer email (e.g ramsan11@gmail.com)");
		}
		if (validation.addressValidation(customer.getCustomerAddress())) {
			flag5 = true;
		} else {
			customerResponse.setMessage5("Please enter valid customer address (e.g sector 24)");
		}
		if (validation.townValidation(customer.getCustomerTown())) {
			flag6 = true;
		} else {
			customerResponse.setMessage6("Please enter valid customer town (e.g Mumbai)");
		}
		if (validation.contactValidation(customer.getCustomerContact())) {
			flag7 = true;
		} else {
			customerResponse.setMessage7("Please enter valid customer contact (e.g 9925146312)");
		}
		if (validation.postalCodeValidation(customer.getCustomerPostalCode())) {
			flag8 = true;
		} else {
			customerResponse.setMessage8("Please enter valid customer password (e.g 445204)");
		}
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7 && flag8) {
			customerResponse.setStatus(200);
			return dao.addCustomer(customer);
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage9("please check customer properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateCustomer(Customer customer, CustomerResponse customerResponse) {
		
		if (validation.idValidation(customer.getCustomerId())) {
			flag1 = true;
		} else {
			customerResponse.setMessage1("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.nameValidation(customer.getCustomerName())) {
			flag2 = true;
		} else {
			customerResponse.setMessage2("Please enter valid customer name (e.g suyash)");
		}
		if (validation.passwordValidation(customer.getCustomerPassword())) {
			flag3 = true;
		} else {
			customerResponse.setMessage3("Please enter valid customer password (e.g Sa*@#$%!11)");
		}
		if (validation.emailValidation(customer.getCustomerEmail())) {
			flag4 = true;
		} else {
			customerResponse.setMessage4("Please enter valid customer email (e.g ramsan11@gmail.com)");
		}
		if (validation.addressValidation(customer.getCustomerAddress())) {
			flag5 = true;
		} else {
			customerResponse.setMessage5("Please enter valid customer address (e.g sector 24)");
		}
		if (validation.townValidation(customer.getCustomerTown())) {
			flag6 = true;
		} else {
			customerResponse.setMessage6("Please enter valid customer town (e.g Mumbai)");
		}
		if (validation.contactValidation(customer.getCustomerContact())) {
			flag7 = true;
		} else {
			customerResponse.setMessage7("Please enter valid customer contact (e.g 9925146312)");
		}
		if (validation.postalCodeValidation(customer.getCustomerPostalCode())) {
			flag8 = true;
		} else {
			customerResponse.setMessage8("Please enter valid customer password (e.g 445204)");
		}
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7 && flag8) {
			customerResponse.setStatus(200);
			return dao.updateCustomer(customer);
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage9("please check customer properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteCustomer(String customerId, CustomerResponse customerResponse) {
		if (validation.idValidation(customerId)) {
			customerResponse.setStatus(200);
			return dao.deleteCustomer(customerId);
		} else {
			customerResponse.setStatus(401);
			customerResponse.setMessage1("Please enter valid customer id (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Customer> serviceGetAllCustomers() {
		return dao.getAllCustomers();
	}
}
