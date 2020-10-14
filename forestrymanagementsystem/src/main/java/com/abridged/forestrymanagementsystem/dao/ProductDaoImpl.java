package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Product;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.ProductAlreadyPresentException;

@Repository
public class ProductDaoImpl implements ProductDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	/**
	 * This method is use to get product record.
	 * 
	 * @param productId is the parameter to getProduct method.
	 * @return record value base on condition, returns record if productId is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Product getProduct(String productId) {
		EntityManager manager = emf.createEntityManager();
		Product record = manager.find(Product.class, productId);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add product record.
	 * 
	 * @param product is the parameter to addProduct method.
	 * @return true value if product is added else throw new
	 *         AlreadyPresentException().
	 */
	@Override
	public boolean addProduct(Product product) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Product record = manager.find(Product.class, product.getProductId());
		if (record != null) {
			throw new ProductAlreadyPresentException();
		}
		manager.persist(product);
		transaction.commit();
		return true;
	}

	/**
	 * This method is use to update product record.
	 * 
	 * @param product is the parameter to updateProduct method.
	 * @return true if product is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateProduct(Product product) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Product p set p.productName=:productName, p.productQuantity=:productQuantity, p.productDescription=:productDescription where p.productId=:productId";
		Query query = manager.createQuery(JPQL);
		query.setParameter("productId", product.getProductId());
		query.setParameter("productName", product.getProductName());
		query.setParameter("productQuantity", product.getProductQuantity());
		query.setParameter("productDescription", product.getProductDescription());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete product record.
	 * 
	 * @param productId is the parameter to deleteProduct method.
	 * @return true if product is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteProduct(String productId) {
		String JPQL = "delete from Product p where p.productId=:productId";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("productId", productId);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all products record.
	 * 
	 * @return productList if productList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		String JPQL = "select p from Product p";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Product> productList = query.getResultList();
		manager.close();
		if (productList.isEmpty()) {
			throw new EmptyRecordException();
		} else {
			return productList;
		}
	}
}
