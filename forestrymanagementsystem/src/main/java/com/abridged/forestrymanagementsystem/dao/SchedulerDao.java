package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Scheduler;

public interface SchedulerDao {
	public Scheduler getScheduler(String schedulerId);

	public boolean addScheduler(Scheduler scheduler);

	public boolean updateScheduler(Scheduler scheduler);

	public boolean deleteScheduler(String schedulerId);
	
	public List<Scheduler> getAllSchedulers();
}
