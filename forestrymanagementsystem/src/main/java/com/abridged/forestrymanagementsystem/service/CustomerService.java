package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Customer;
import com.abridged.forestrymanagementsystem.dto.CustomerResponse;

public interface CustomerService {
	public boolean serviceLoginCustomer(String customerName,String customerPassword, CustomerResponse customerResponse);

	public Customer serviceGetCustomer(String customerId, CustomerResponse customerResponse);

	public boolean serviceAddCustomer(Customer customer, CustomerResponse customerResponse);

	public boolean serviceUpdateCustomer(Customer customer, CustomerResponse customerResponse);

	public boolean serviceDeleteCustomer(String customerId, CustomerResponse customerResponse);

	public List<Customer> serviceGetAllCustomers();
}
