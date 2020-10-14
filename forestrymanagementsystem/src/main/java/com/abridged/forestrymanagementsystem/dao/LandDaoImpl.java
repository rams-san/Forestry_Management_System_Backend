package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.abridged.forestrymanagementsystem.dto.Land;
import com.abridged.forestrymanagementsystem.exception.EmptyRecordException;
import com.abridged.forestrymanagementsystem.exception.LandAlreadyPresentException;
import com.abridged.forestrymanagementsystem.exception.NotFoundException;

@Repository
public class LandDaoImpl implements LandDao {

	@PersistenceUnit
	private EntityManagerFactory emf;

	/**
	 * This method is use to get land record.
	 * 
	 * @param surveyNumber is the parameter to getLand method.
	 * @return record value base on condition, returns record if surveyNumber is
	 *         present else throw new NotFoundException().
	 */
	@Override
	public Land getLand(String surveyNumber) {
		EntityManager manager = emf.createEntityManager();
		Land record = manager.find(Land.class, surveyNumber);
		manager.close();
		if(record!=null) {
			return record;
		}
		else throw new NotFoundException();
	}

	/**
	 * This method is use to add land record.
	 * 
	 * @param land is the parameter to addLand method.
	 * @return true value if land is added else throw new
	 *         AlreadyPresentException().
	 */
	@Override
	public boolean addLand(Land land) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Land record = manager.find(Land.class, land.getSurveyNumber());
		if (record != null) {
			throw new LandAlreadyPresentException();
		}
			 manager.persist(land);
			transaction.commit();
			return true;
	}

	/**
	 * This method is use to update land record.
	 * 
	 * @param land is the parameter to updateLand method.
	 * @return true if land is updated else throw new NotFoundException().
	 */
	@Override
	public boolean updateLand(Land land) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		String JPQL = "update Land l set l.ownerName=:ownerName, l.landArea=:landArea where l.surveyNumber=:surveyNumber";
		Query query = manager.createQuery(JPQL);
		query.setParameter("surveyNumber", land.getSurveyNumber());
		query.setParameter("ownerName", land.getOwnerName());
		query.setParameter("landArea", land.getLandArea());
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
		throw new NotFoundException();
	}

	/**
	 * This method is use to delete land record.
	 * 
	 * @param surveyNumber is the parameter to deleteLand method.
	 * @return true if land is deleted else throw new NotFoundException().
	 */
	@Override
	public boolean deleteLand(String surveyNumber) {
		String JPQL = "delete from Land l where l.surveyNumber=:surveyNumber";

		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery(JPQL);
		query.setParameter("surveyNumber", surveyNumber);
		int i = query.executeUpdate();
		transaction.commit();
		if (i != 0) {
			return true;
		}
			throw new NotFoundException();
	}

	/**
	 * This method is use to get all lands record.
	 * 
	 * @return landList if landList is not empty else throw new
	 *         EmptyRecordException().
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Land> getAllLands() {
		String JPQL = "select l from Land l";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(JPQL);
		List<Land> landList = query.getResultList();
		manager.close();
		if(landList.isEmpty()) {
			throw new EmptyRecordException();
		}
		else {
			return landList;
		}
	}

}
