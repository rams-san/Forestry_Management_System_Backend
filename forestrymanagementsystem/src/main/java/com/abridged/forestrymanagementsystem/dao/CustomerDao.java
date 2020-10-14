package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Customer;


public interface CustomerDao {
	public boolean loginCustomer(String customerName, String customerPassword);

	public Customer getCustomer(String customerId);

	public boolean addCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public boolean deleteCustomer(String customerId);
	
	public List<Customer> getAllCustomers();
}
