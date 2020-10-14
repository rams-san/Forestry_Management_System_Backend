package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.AdminDao;
import com.abridged.forestrymanagementsystem.dto.Admin;
import com.abridged.forestrymanagementsystem.dto.AdminResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao dao;

	static ValidationImpl validation = new ValidationImpl();

	@Override
	public boolean serviceLoginAdmin(String adminName, String adminPassword, AdminResponse adminResponse) {
		String validName = null;
		String validPassword = null;
		boolean flag1 = false;
		boolean flag2 = false;
		if (validation.nameValidation(adminName)) {
			validName = adminName;
			flag1 = true;
		} else {
			adminResponse.setMessage("Please enter valid admin name (e.g suyash)");
		}
		if (validation.passwordValidation(adminPassword)) {
			validPassword = adminPassword;
			flag2 = true;
		} else {
			adminResponse.setMessage1("Please enter valid admin password (e.g Sa*@#$%!11)");
		}
		if (flag1 && flag2) {
			adminResponse.setStatus(200);
			return dao.loginAdmin(validName, validPassword);
		} else {
			adminResponse.setStatus(401);
			adminResponse.setMessage2("Login failed, please check admin name and password");
		}
		return false;
	}

	@Override
	public Admin serviceGetAdmin(String adminName, AdminResponse adminResponse) {
		
		if (validation.nameValidation(adminName)) {
			adminResponse.setStatus(200);
			return dao.getAdmin(adminName);
		} else {
			adminResponse.setStatus(401);
			adminResponse.setMessage1("Please enter valid admin name (e.g suyash)");
		}
		return null;
	}

	@Override
	public boolean serviceAddAdmin(Admin admin, AdminResponse adminResponse) {
		boolean flag1 = false;
		boolean flag2 = false;
		if (validation.nameValidation(admin.getAdminName())) {
			flag1 = true;
		} else {
			adminResponse.setMessage1("Please enter valid admin name (e.g suyash)");
		}
		if (validation.passwordValidation(admin.getAdminPassword())) {
			flag2 = true;
		} else {
			adminResponse.setMessage2("Please enter valid admin password (e.g Sa*@#$%!11)");
		}
		if (flag1 && flag2) {
			adminResponse.setStatus(200);
			return dao.addAdmin(admin);
		} else {
			adminResponse.setStatus(401);
			adminResponse.setMessage3("please check admin name and password");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateAdmin(Admin admin, AdminResponse adminResponse) {
		boolean flag1 = false;
		boolean flag2 = false;
		if (validation.nameValidation(admin.getAdminName())) {
			flag1 = true;
		} else {
			adminResponse.setMessage1("Please enter valid admin name (e.g suyash)");
		}
		if (validation.passwordValidation(admin.getAdminPassword())) {
			flag2 = true;
		} else {
			adminResponse.setMessage2("Please enter valid admin password (e.g Sa*@#$%!11)");
		}
		if (flag1 && flag2) {
			adminResponse.setStatus(200);
			return dao.updateAdmin(admin);

		} else {
			adminResponse.setStatus(401);
			adminResponse.setMessage3("please check admin name and password");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteAdmin(String adminName, AdminResponse adminResponse) {
		if (validation.nameValidation(adminName)) {
			adminResponse.setStatus(200);
			return dao.deleteAdmin(adminName);
		} else {
			adminResponse.setStatus(401);
			adminResponse.setMessage1("Please enter valid admin name (e.g suyash)");
		}
		return false;
	}

	@Override
	public List<Admin> serviceGetAllAdmins() {
		return dao.getAllAdmins();
	}
}
