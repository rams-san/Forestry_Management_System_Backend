package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.SchedulerDao;
import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.dto.SchedulerResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerDao dao;

	static ValidationImpl validation = new ValidationImpl();

	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;

	@Override
	public Scheduler serviceGetScheduler(String schedulerId, SchedulerResponse schedulerResponse) {
		if (validation.idValidation(schedulerId)) {
			schedulerResponse.setStatus(200);
			return dao.getScheduler(schedulerId);
		} else {
			schedulerResponse.setStatus(401);
			schedulerResponse.setMessage1("Please enter valid scheduler id (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddScheduler(Scheduler scheduler, SchedulerResponse schedulerResponse) {
		if (validation.idValidation(scheduler.getSchedulerId())) {
			flag1 = true;
		} else {
			schedulerResponse.setMessage1("Please enter valid scheduler id (e.g range{1,4})");
		}
		if (validation.nameValidation(scheduler.getSchedulerName())) {
			flag2 = true;
		} else {
			schedulerResponse.setMessage2("Please enter valid scheduler name (e.g suyash)");
		}
		if (validation.truckNumberValidation(scheduler.getTruckNumber())) {
			flag3 = true;
		} else {
			schedulerResponse.setMessage3("Please enter valid truck number (e.g MH 29 5456)");
		}
		if (validation.contactValidation(scheduler.getSchedulerContact())) {
			flag4 = true;
		} else {
			schedulerResponse.setMessage4("Please enter valid scheduler contact(e.g 8465146685)");
		}
		if (flag1 && flag2 && flag3 && flag4 && flag4) {
			schedulerResponse.setStatus(200);
			return dao.addScheduler(scheduler);
		} else {
			schedulerResponse.setStatus(401);
			schedulerResponse.setMessage5("please check scheduler properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateScheduler(Scheduler scheduler, SchedulerResponse schedulerResponse) {
		if (validation.idValidation(scheduler.getSchedulerId())) {
			flag1 = true;
		} else {
			schedulerResponse.setMessage1("Please enter valid scheduler id (e.g range{1,4})");
		}
		if (validation.nameValidation(scheduler.getSchedulerName())) {
			flag2 = true;
		} else {
			schedulerResponse.setMessage2("Please enter valid scheduler name (e.g suyash)");
		}
		if (validation.truckNumberValidation(scheduler.getTruckNumber())) {
			flag3 = true;
		} else {
			schedulerResponse.setMessage3("Please enter valid truck number (e.g MH 29 5456)");
		}
		if (validation.contactValidation(scheduler.getSchedulerContact())) {
			flag4 = true;
		} else {
			schedulerResponse.setMessage4("Please enter valid scheduler contact(e.g 8465146685)");
		}
		if (flag1 && flag2 && flag3 && flag4 && flag4) {
			schedulerResponse.setStatus(200);
			return dao.updateScheduler(scheduler);
		} else {
			schedulerResponse.setStatus(401);
			schedulerResponse.setMessage5("please check scheduler properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteScheduler(String schedulerId, SchedulerResponse schedulerResponse) {
		if (validation.idValidation(schedulerId)) {
			schedulerResponse.setStatus(200);
			return dao.deleteScheduler(schedulerId);
		} else {
			schedulerResponse.setStatus(401);
			schedulerResponse.setMessage1("Please enter valid scheduler id (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Scheduler> serviceGetAllSchedulers() {
		return dao.getAllSchedulers();
	}

}
