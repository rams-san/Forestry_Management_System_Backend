package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Contract;
import com.abridged.forestrymanagementsystem.dto.Customer;
import com.abridged.forestrymanagementsystem.dto.Orders;
import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.exception.ContractNumberAbsentException;
import com.abridged.forestrymanagementsystem.exception.ContractNumberAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.CustomerIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.OrderNumberAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.ProductIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.SchedulerIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.SchedulerIdAlreadyPresentException;

@Repository
public class OrderDaoImpl implements OrderDao {

	@PersistenceUnit
	private EntityManagerFactory emf;
	int count1=0;
	int count2=0;
	int count3=0;
	int count4=0;


	/**
	 * This method is use to get order record.
	 * 
	 * @param orderNumber is the parameter to getOrder method.
	 * @return record value base on condition, returns record if orderNumber is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Orders getOrder(String orderNumber) {
		EntityManager manager = emf.createEntityManager();
		Orders record = manager.find(Orders.class, orderNumber);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add order record.
	 * 
	 * @param order is the parameter to addOrder method.
	 * @return true value if order is added else throw new
	 *         AlreadyPresentException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addOrder(Orders order) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String JPQL = "select o from Orders o";
		String JPQL1 = "select c from Scheduler c";
		String JPQL2 = "select p from Product p";
		String JPQL3 = "select cu from Customer cu";
		String JPQL4 = "select co from Contract co";


		Query query = manager.createQuery(JPQL);
		Query query1 = manager.createQuery(JPQL1);
		Query query2 = manager.createQuery(JPQL2);
		Query query3 = manager.createQuery(JPQL3);
		Query query4 = manager.createQuery(JPQL4);

		List<Orders> orderList1 = query.getResultList();
		List<Scheduler> schedulerList = query1.getResultList();
		List<Product> productList = query2.getResultList();
		List<Customer> customerList = query3.getResultList();
		List<Contract> contractList = query4.getResultList();


		for (Scheduler scheduler : schedulerList) {
			if ((scheduler.getSchedulerId().equals(order.getSchedulerId()))) {
				count1++;
			} 
		}

		if(count1==0) {
			throw new SchedulerIdAbsentException();
		}
		
		for (Product product : productList) {
			if ((product.getProductId().equals(order.getProductId()))) {
				count2++;
			} 
		}

		if(count2==0) {
			throw new ProductIdAbsentException();
		}
		
		for (Customer customer : customerList) {
			if ((customer.getCustomerId().equals(order.getCustomerId()))) {
				count3++;
			}
		}
		
		if(count3==0) {
			throw new CustomerIdAbsentException();
		}
		
		for (Contract contract : contractList) {
			if ((contract.getContractNumber().equals(order.getContractNumber()))) {
				count4++;
			}
		}
		
		if(count4==0) {
			throw new ContractNumberAbsentException();
		}
		
		if ((count1!=0) && (count2!=0) && (count3!=0) && (count4!=0)) {
			for (Orders order1 : orderList1) {
				if (order1.getSchedulerId().equals(order.getSchedulerId())) {
					throw new SchedulerIdAlreadyPresentException();
				}
			}

			for (Orders order2 : orderList1) {
				if (order2.getContractNumber().equals(order.getContractNumber())) {
					throw new ContractNumberAlreadyPresentException();
				}
			}
			Orders record = manager.find(Orders.class, order.getOrderNumber());

			if (record != null) {
				throw new OrderNumberAlreadyPresentException();
			}

			manager.persist(order);
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is use to update order record.
	 * 
	 * @param order is the parameter to updateOrder method.
	 * @return true if order is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateOrder(Orders order) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Orders o set o.customerId=:customerId, o.productId=:productId, o.deliveryPlace=:deliveryPlace, o.deliveryDate=:deliveryDate, o.quantity=:quantity, o.schedulerId=:schedulerId, o.contractNumber=:contractNumber where o.orderNumber=:orderNumber";
		Query query = manager.createQuery(JPQL);
		query.setParameter("orderNumber", order.getOrderNumber());
		query.setParameter("customerId", order.getCustomerId());
		query.setParameter("productId", order.getProductId());
		query.setParameter("deliveryPlace", order.getDeliveryPlace());
		query.setParameter("deliveryDate", order.getDeliveryDate());
		query.setParameter("quantity", order.getQuantity());
		query.setParameter("schedulerId", order.getSchedulerId());
		query.setParameter("contractNumber", order.getContractNumber());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete order record.
	 * 
	 * @param orderNumber is the parameter to deleteOrder method.
	 * @return true if order is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteOrder(String orderNumber) {
		String JPQL = "delete from Orders o where o.orderNumber=:orderNumber";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("orderNumber", orderNumber);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all orders record.
	 * 
	 * @return orderList if orderList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getAllOrders() {
		String JPQL = "select o from Orders o";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Orders> orderList = query.getResultList();
		manager.close();
		if (orderList.isEmpty()) {
			throw new EmptyRecordException();
		} else {
			return orderList;
		}
	}

}
