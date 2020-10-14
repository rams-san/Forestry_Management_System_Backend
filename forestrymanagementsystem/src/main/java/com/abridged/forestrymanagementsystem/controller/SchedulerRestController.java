package com.abridged.forestrymanagementsystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.abridged.forestrymanagementsystem.dto.Scheduler;
import com.abridged.forestrymanagementsystem.dto.SchedulerResponse;
import com.abridged.forestrymanagementsystem.service.SchedulerService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SchedulerRestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true);

		binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private SchedulerService service;

	/**
	 * This method is use to get scheduler record.
	 * 
	 * @param schedulerId is the parameter to getSchedulerBySchedulerId method.
	 * @return schedulerResponse
	 */
	@GetMapping(path = "/getscheduler", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SchedulerResponse getSchedulerBySchedulerId(String schedulerId) {
		SchedulerResponse schedulerResponse = new SchedulerResponse();
		Scheduler info = service.serviceGetScheduler(schedulerId, schedulerResponse);

		if (info != null) {
			schedulerResponse.setError(false);
			schedulerResponse.setMessage("Get the record");
			schedulerResponse.setScheduler(info);
		} else {
			schedulerResponse.setError(true);
			schedulerResponse.setMessage("Scheduler id is not present");
			schedulerResponse.setScheduler(info);
		}
		return schedulerResponse;
	}// End of getSchedulerBySchedulerId()

	/**
	 * This method is use to add scheduler record.
	 * 
	 * @param scheduler is the parameter to addScheduler method.
	 * @return schedulerResponse
	 */
	@PostMapping(path = "/addscheduler", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public SchedulerResponse addScheduler(@RequestBody Scheduler scheduler) {
		SchedulerResponse schedulerResponse = new SchedulerResponse();
		boolean isAdded = service.serviceAddScheduler(scheduler, schedulerResponse);

		if (isAdded) {
			schedulerResponse.setError(false);
			schedulerResponse.setMessage("Scheduler record added successfully");
		} else {
			schedulerResponse.setError(true);
			schedulerResponse.setMessage("Record id not added");
		}
		return schedulerResponse;
	}// End of addScheduler()

	/**
	 * This method is use to delete scheduler record.
	 * 
	 * @param schedulerId is the parameter to deleteSchedulerBySchedulerId method.
	 * @return schedulerResponse
	 */
	@DeleteMapping(path = "/deletescheduler", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SchedulerResponse deleteSchedulerBySchedulerId(String schedulerId) {
		SchedulerResponse schedulerResponse = new SchedulerResponse();
		boolean isDeleted = service.serviceDeleteScheduler(schedulerId, schedulerResponse);

		if (isDeleted) {
			schedulerResponse.setError(false);
			schedulerResponse.setMessage("Scheduler record deleted successfully");
		} else {
			schedulerResponse.setError(true);
			schedulerResponse.setMessage("Record id not deleted");
		}
		return schedulerResponse;
	}// End of deleteSchedulerBySchedulerId()

	/**
	 * This method is use to update scheduler record.
	 * 
	 * @param scheduler is the parameter to updateScheduler method.
	 * @return schedulerResponse
	 */
	@PutMapping(path = "/updatescheduler", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public SchedulerResponse updateScheduler(@RequestBody Scheduler scheduler) {
		SchedulerResponse schedulerResponse = new SchedulerResponse();
		boolean isUpdated = service.serviceUpdateScheduler(scheduler, schedulerResponse);

		if (isUpdated) {
			schedulerResponse.setError(false);
			schedulerResponse.setMessage("Scheduler record updated successfully");
		} else {
			schedulerResponse.setError(true);
			schedulerResponse.setMessage("Record id not updated");
		}
		return schedulerResponse;
	}// End of updateScheduler()

	/**
	 * This method is use to get all schedulers record.
	 * 
	 * @return schedulerResponse
	 */
	@GetMapping(path = "getallschedulersrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SchedulerResponse getAllSchedulersDetails() {
		List<Scheduler> listRecord = service.serviceGetAllSchedulers();
		SchedulerResponse schedulerResponse = new SchedulerResponse();
		if (listRecord != null) {
			schedulerResponse.setError(false);
			schedulerResponse.setMessage("All Product record");
			schedulerResponse.setSchedulerListInfo(listRecord);
		} else {
			schedulerResponse.setError(true);
			schedulerResponse.setMessage("Product record is not present");
			schedulerResponse.setSchedulerListInfo(listRecord);
		}
		return schedulerResponse;

	}// End of getAllSchedulersDetails()
}// End of the class
