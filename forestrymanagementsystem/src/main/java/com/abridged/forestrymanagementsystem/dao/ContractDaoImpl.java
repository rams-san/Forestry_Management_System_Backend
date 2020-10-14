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
import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.exception.ContractNumberAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.CustomerIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.ProductIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.SchedulerIdAbsentException;
import com.abridged.forestrymanagementsystem.exception.SchedulerIdAlreadyPresentException;

@Repository
public class ContractDaoImpl implements ContractDao {

	@PersistenceUnit
	private EntityManagerFactory emf;
	int count1=0;
	int count2=0;
	int count3=0;
	/**
	 * This method is use to get contract record.
	 * 
	 * @param contractNumber is the parameter to getContract method.
	 * @return record value base on condition, returns record if contractNumber is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Contract getContract(String contractNumber) {
		EntityManager manager = emf.createEntityManager();
		Contract record = manager.find(Contract.class, contractNumber);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add contract record.
	 * 
	 * @param contract is the parameter to addContract method.
	 * @return true value if contract is added else throw new
	 *         AlreadyPresentException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addContract(Contract contract) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String JPQL = "select c from Contract c";
		String JPQL1 = "select s from Scheduler s";
		String JPQL2 = "select p from Product p";
		String JPQL3 = "select cu from Customer cu";
		
		Query query = manager.createQuery(JPQL);
		Query query1 = manager.createQuery(JPQL1);
		Query query2 = manager.createQuery(JPQL2);
		Query query3 = manager.createQuery(JPQL3);
		
		List<Contract> contractList = query.getResultList();
		List<Scheduler> schedulerList = query1.getResultList();
		List<Product> productList = query2.getResultList();
		List<Customer> customerList = query3.getResultList();
		
		for (Scheduler scheduler : schedulerList) {
			if ((scheduler.getSchedulerId().equals(contract.getSchedulerId()))) {
				count1++;
			} 
		}

		if(count1==0) {
			throw new SchedulerIdAbsentException();
		}
		
		for (Product product : productList) {
			if ((product.getProductId().equals(contract.getProductId()))) {
				count2++;
			} 
		}

		if(count2==0) {
			throw new ProductIdAbsentException();
		}
		
		for (Customer customer : customerList) {
			if ((customer.getCustomerId().equals(contract.getCustomerId()))) {
				count3++;
			}
		}

		if(count3==0) {
			throw new CustomerIdAbsentException();
		}
		
		if ((count1 != 0) && (count2 != 0) && (count3 != 0)) {
			for (Contract contract1 : contractList) {
				if (contract1.getSchedulerId().equals(contract.getSchedulerId())) {
					throw new SchedulerIdAlreadyPresentException();
				}
			}

			Contract record = manager.find(Contract.class, contract.getContractNumber());
			if (record != null) {
				throw new ContractNumberAlreadyPresentException();
			}
			manager.persist(contract);
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is use to update contract record.
	 * 
	 * @param contract is the parameter to updateContract method.
	 * @return true if contract is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateContract(Contract contract) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Contract c set c.customerId=:customerId, c.productId=:productId, c.deliveryPlace=:deliveryPlace, c.deliveryDate=:deliveryDate, c.quantity=:quantity, c.schedulerId=:schedulerId where c.contractNumber=:contractNumber";
		Query query = manager.createQuery(JPQL);
		query.setParameter("contractNumber", contract.getContractNumber());
		query.setParameter("customerId", contract.getCustomerId());
		query.setParameter("productId", contract.getProductId());
		query.setParameter("deliveryPlace", contract.getDeliveryPlace());
		query.setParameter("deliveryDate", contract.getDeliveryDate());
		query.setParameter("quantity", contract.getQuantity());
		query.setParameter("schedulerId", contract.getSchedulerId());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete contract record.
	 * 
	 * @param contractNumber is the parameter to deleteContract method.
	 * @return true if contract is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteContract(String contractNumber) {
		String JPQL = "delete from Contract c where c.contractNumber=:contractNumber";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("contractNumber", contractNumber);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all contracts record.
	 * 
	 * @return contractList if contractList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contract> getAllContracts() {
		String JPQL = "select c from Contract c";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Contract> contractList = query.getResultList();
		manager.close();
		if (contractList.isEmpty()) {
			throw new EmptyRecordException();
		} else {
			return contractList;
		}
	}

}
