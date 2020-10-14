package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Admin;

public interface AdminDao {
	public boolean loginAdmin(String adminName, String adminPassword);
	
	public Admin getAdmin(String adminName);

	public boolean addAdmin(Admin admin);
	
	public boolean updateAdmin(Admin admin);

	public boolean deleteAdmin(String adminName);
	
	public List<Admin> getAllAdmins();
}
