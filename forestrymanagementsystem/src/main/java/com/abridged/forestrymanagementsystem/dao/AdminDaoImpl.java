package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Admin;
import com.abridged.forestrymanagementsystem.exception.AdminAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.ValidationException;

@Repository
public class AdminDaoImpl implements AdminDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	
	/**
	 * This method is use to login as admin.
	 * 
	 * @param adminName and adminPassword are the parameters to loginAdmin method.
	 * @return record value if condition is satisfied else throw new ValidationException()
	 */
	@Override
	public boolean loginAdmin(String adminName, String adminPassword) {
		int count = 0;
		String JPQL = "select a from Admin a";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		@SuppressWarnings("unchecked")
		List<Admin> adminList = query.getResultList();
		manager.close();
		for (Admin admin : adminList) {
			if ((admin.getAdminName().equals(adminName)) && (admin.getAdminPassword().equals(adminPassword))) {
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
	 * This method is use to get admin record.
	 * 
	 * @param adminName is the parameter to getAdmin method.
	 * @return record value base on condition, returns record if adminName is present
	 *         else throw new NotFoundException().
	 */
	@Override
	public Admin getAdmin(String adminName) {
		EntityManager manager = emf.createEntityManager();
		Admin record = manager.find(Admin.class, adminName);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add admin record.
	 * 
	 * @param admin is the parameter to addAdmin method.
	 * @return true value if admin is added else throw new
	 *         AlreadyPresentException().
	 */
	@Override
	public boolean addAdmin(Admin admin) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Admin record = manager.find(Admin.class, admin.getAdminName());
		if (record != null) {
			throw new AdminAlreadyPresentException();
		}
			manager.persist(admin);
			transaction.commit();
			return true;
	}

	/**
	 * This method is use to update admin record.
	 * 
	 * @param admin is the parameter to updateCustomer method.
	 * @return true if admin is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateAdmin(Admin admin) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Admin a set a.adminPassword=:adminPassword where a.adminName=:adminName";
		Query query = manager.createQuery(JPQL);
		query.setParameter("adminName", admin.getAdminName());
		query.setParameter("adminPassword", admin.getAdminPassword());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete admin record.
	 * 
	 * @param adminName is the parameter to deleteAdmin method.
	 * @return true if admin is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteAdmin(String adminName) {
		String JPQL = "delete from Admin a where a.adminName=:adminName";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("adminName", adminName);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all admins record.
	 * 
	 * @return adminList if adminList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAllAdmins() {
		String JPQL = "select a from Admin a";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Admin> adminList = query.getResultList();
		manager.close();
		if (adminList.isEmpty()) {
			throw new EmptyRecordException();
		} else {
			return adminList;
		}
	}
}
