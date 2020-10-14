package com.abridged.forestrymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abridged.forestrymanagementsystem.dto.Admin;
import com.abridged.forestrymanagementsystem.dto.AdminResponse;
import com.abridged.forestrymanagementsystem.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminRestController {

	@Autowired
	private AdminService service;

	/**
	 * This method is use to login as admin.
	 * 
	 * @param adminName and adminPassword are the parameters to loginAdmin method.
	 * @return adminResponse
	 */
	@GetMapping(path = "/loginadmin", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public AdminResponse loginAdmin(String adminName, String adminPassword) {
		AdminResponse adminResponse = new AdminResponse();
		System.out.println(adminName+" + "+adminPassword);
		boolean login = service.serviceLoginAdmin(adminName, adminPassword, adminResponse);
		if (login) {
			adminResponse.setMessage("Login successful");
		}
		return adminResponse;
	}// End of loginAdmin()

	/**
	 * This method is use to get admin record.
	 * 
	 * @param adminName is the parameter to getAdminByName method.
	 * @return adminResponse
	 */
	@GetMapping(path = "/getadmin", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public AdminResponse getAdminByName(String adminName) {
		AdminResponse adminResponse = new AdminResponse();
		System.out.println(adminName);
		Admin info = service.serviceGetAdmin(adminName, adminResponse);

		if (info != null) {
			adminResponse.setError(false);
			adminResponse.setMessage("Get the record");
			adminResponse.setAdmin(info);
		} else {
			adminResponse.setError(true);
			adminResponse.setMessage("Admin is not present");
			adminResponse.setAdmin(info);
		}
		return adminResponse;
	}// End of getAdminByName()

	/**
	 * This method is use to add admin record.
	 * 
	 * @param admin is the parameter to addAdmin method.
	 * @return adminResponse
	 */
	@PostMapping(path = "/addadmin", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public AdminResponse addAdmin(@RequestBody Admin admin) {
		AdminResponse adminResponse = new AdminResponse();
		boolean isAdded = service.serviceAddAdmin(admin, adminResponse);

		if (isAdded) {
			adminResponse.setError(false);
			adminResponse.setMessage("Admin record added successfully");
		} else {
			adminResponse.setError(true);
			adminResponse.setMessage("Record id not added");
		}
		return adminResponse;
	}// End of addAdmin()

	/**
	 * This method is use to delete admin record.
	 * 
	 * @param adminName is the parameter to deleteAdminById method.
	 * @return adminResponse
	 */
	@DeleteMapping(path = "/deleteadmin", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public AdminResponse deleteAdminByName( String adminName) {
		AdminResponse adminResponse = new AdminResponse();
		System.out.println(adminName);
		boolean isDeleted = service.serviceDeleteAdmin(adminName, adminResponse);

		if (isDeleted) {
			adminResponse.setError(false);
			adminResponse.setMessage("Admin record deleted successfully");
		} else {
			adminResponse.setError(true);
			adminResponse.setMessage("Record is not deleted");
		}
		return adminResponse;
	}// End of deleteAdminByName()

	/**
	 * This method is use to update admin record.
	 * 
	 * @param admin is the parameter to updateAdmin method.
	 * @return adminResponse
	 */
	@PutMapping(path = "/updateadmin", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public AdminResponse updateAdmin(@RequestBody Admin admin) {
		AdminResponse adminResponse = new AdminResponse();
		boolean isUpdated = service.serviceUpdateAdmin(admin, adminResponse);

		if (isUpdated) {
			adminResponse.setError(false);
			adminResponse.setMessage("Admin record updated successfully");
		} else {
			adminResponse.setError(true);
			adminResponse.setMessage("Record is not updated");
		}
		return adminResponse;
	}// End of updateAdmin()

	/**
	 * This method is use to get all admins record.
	 * 
	 * @return adminResponse
	 */
	@GetMapping(path = "/getalladminsrecord", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public AdminResponse getAllAdminsDetails() {
		List<Admin> listRecord = service.serviceGetAllAdmins();
		AdminResponse adminResponse = new AdminResponse();
		if (listRecord != null) {
			adminResponse.setError(false);
			adminResponse.setMessage("All Customer record");
			adminResponse.setAdminListInfo(listRecord);
		} else {
			adminResponse.setError(true);
			adminResponse.setMessage("Customer record is not present");
			adminResponse.setAdminListInfo(listRecord);
		}
		return adminResponse;
	}// End of getAllAdminsDetails
}// End of the class
