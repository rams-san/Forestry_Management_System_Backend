package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.dto.SchedulerResponse;

public interface SchedulerService {
	public Scheduler serviceGetScheduler(String schedulerId, SchedulerResponse schedulerResponse);

	public boolean serviceAddScheduler(Scheduler scheduler, SchedulerResponse schedulerResponse);

	public boolean serviceUpdateScheduler(Scheduler scheduler, SchedulerResponse schedulerResponse);

	public boolean serviceDeleteScheduler(String schedulerId, SchedulerResponse schedulerResponse);

	public List<Scheduler> serviceGetAllSchedulers();
}
