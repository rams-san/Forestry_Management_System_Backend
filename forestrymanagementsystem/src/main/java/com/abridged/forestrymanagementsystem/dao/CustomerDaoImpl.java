package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Customer;
import com.abridged.forestrymanagementsystem.exception.CustomerIdAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.ValidationException;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	/**
	 * This method is use to login as customer.
	 * 
	 * @param customerId and customerPassword are the parameters to loginCustomer method.
	 * @return record value if condition is satisfied else throw new ValidationException()
	 */
	@Override
	public boolean loginCustomer(String customerName, String customerPassword) {
		int count = 0;
		String JPQL = "select c from Customer c";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		@SuppressWarnings("unchecked")
		List<Customer> customerList = query.getResultList();
		manager.close();
		for (Customer customer : customerList) {
			if ((customer.getCustomerName().equals(customerName)) && (customer.getCustomerPassword().equals(customerPassword))) {
				count++;
			}
		}
		if (count != 0) {
			return true;
		} else {
			throw new ValidationException();
		}
	}
	
	/**
	 * This method is use to get customer record.
	 * 
	 * @param customerId is the parameter to getCustomer method.
	 * @return record value base on condition, returns record if customerId is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Customer getCustomer(String customerId) {
		EntityManager manager = emf.createEntityManager();
		Customer record = manager.find(Customer.class, customerId);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add customer record.
	 * 
	 * @param customer is the parameter to addCustomer method.
	 * @return true value if customer is added else throw new
	 *         AlreadyPresentException().
	 */
	@Override
	public boolean addCustomer(Customer customer) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		System.out.println(customer);
		transaction.begin();
		Customer record = manager.find(Customer.class, customer.getCustomerId());
		if (record != null) {
			throw new CustomerIdAlreadyPresentException();
		}
			manager.persist(customer);
			transaction.commit();
			return true;
	}

	/**
	 * This method is use to update customer record.
	 * 
	 * @param customer is the parameter to updateCustomer method.
	 * @return true if customer is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateCustomer(Customer customer) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Customer c set c.customerName=:customerName, c.customerPassword=:customerPassword, c.customerEmail=:customerEmail, c.customerAddress=:customerAddress, c.customerTown=:customerTown, c.customerPostalCode=:customerPostalCode, c.customerContact=:customerContact where c.customerId=:customerId";
		Query query = manager.createQuery(JPQL);
		query.setParameter("customerId", customer.getCustomerId());
		query.setParameter("customerPassword", customer.getCustomerPassword());
		query.setParameter("customerName", customer.getCustomerName());
		query.setParameter("customerEmail", customer.getCustomerEmail());
		query.setParameter("customerAddress", customer.getCustomerAddress());
		query.setParameter("customerTown", customer.getCustomerTown());
		query.setParameter("customerPostalCode", customer.getCustomerPostalCode());
		query.setParameter("customerContact", customer.getCustomerContact());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete customer record.
	 * 
	 * @param customerId is the parameter to deleteCustomer method.
	 * @return true if customer is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteCustomer(String customerId) {
		String JPQL = "delete from Customer c where c.customerId=:customerId";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("customerId", customerId);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all customers record.
	 * 
	 * @return customerList if customerList is not empty else throw new EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		String JPQL = "select c from Customer c";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Customer> customerList = query.getResultList();
		manager.close();
		if (customerList.isEmpty()) {
			throw new EmptyRecordException();
		} else {
			return customerList;
		}
	}
}
