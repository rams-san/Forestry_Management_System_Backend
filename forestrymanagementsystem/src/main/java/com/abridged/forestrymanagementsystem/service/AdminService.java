package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Admin;
import com.abridged.forestrymanagementsystem.dto.AdminResponse;

public interface AdminService {
	public boolean serviceLoginAdmin(String adminName,String adminPassword, AdminResponse adminResponse);

	public Admin serviceGetAdmin(String adminName, AdminResponse adminResponse);

	public boolean serviceAddAdmin(Admin admin, AdminResponse adminResponse);
	
	public boolean serviceUpdateAdmin(Admin admin, AdminResponse adminResponse);

	public boolean serviceDeleteAdmin(String adminName, AdminResponse adminResponse);

	public List<Admin> serviceGetAllAdmins();
	
}
