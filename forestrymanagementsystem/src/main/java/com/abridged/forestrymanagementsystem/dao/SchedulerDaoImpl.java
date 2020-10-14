package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;
import com.abridged.forestrymanagementsystem.exception.SchedulerAlreadyPresentException;

@Repository
public class SchedulerDaoImpl implements SchedulerDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	/**
	 * This method is use to get scheduler record.
	 * 
	 * @param schedulerId is the parameter to getScheduler method.
	 * @return record value base on condition, returns record if schedulerId is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Scheduler getScheduler(String schedulerId) {
		EntityManager manager = emf.createEntityManager();
		Scheduler record = manager.find(Scheduler.class, schedulerId);
		manager.close();
		if (record != null) {
			return record;
		} else
			throw new NotFoundException();
	}

	/**
	 * This method is use to add scheduler record.
	 * 
	 * @param scheduler is the parameter to addScheduler method.
	 * @return true value if scheduler is added else throw new
	 *         AlreadyPresentException().
	 */
	@Override
	public boolean addScheduler(Scheduler scheduler) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Scheduler record = manager.find(Scheduler.class, scheduler.getSchedulerId());
		if (record != null) {
			throw new SchedulerAlreadyPresentException();
		}
			manager.persist(scheduler);
			transaction.commit();
			return true;	
	}

	/**
	 * This method is use to update scheduler record.
	 * 
	 * @param scheduler is the parameter to updateScheduler method.
	 * @return true if scheduler is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateScheduler(Scheduler scheduler) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Scheduler s set s.schedulerName=:schedulerName, s.schedulerContact=:schedulerContact, s.truckNumber=:truckNumber where s.schedulerId=:schedulerId";
		Query query = manager.createQuery(JPQL);
		query.setParameter("schedulerId", scheduler.getSchedulerId());
		query.setParameter("schedulerName", scheduler.getSchedulerName());
		query.setParameter("schedulerContact", scheduler.getSchedulerContact());
		query.setParameter("truckNumber", scheduler.getTruckNumber());

		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete scheduler record.
	 * 
	 * @param schedulerId is the parameter to deleteScheduler method.
	 * @return true if scheduler is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteScheduler(String schedulerId) {
		String JPQL = "delete from Scheduler s where s.schedulerId=:schedulerId";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("schedulerId", schedulerId);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to get all schedulers record.
	 * 
	 * @return schedulerList if schedulerList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Scheduler> getAllSchedulers() {
		String JPQL = "select s from Scheduler s";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Scheduler> schedulerList = query.getResultList();
		manager.close();
		return schedulerList;
	}

}
