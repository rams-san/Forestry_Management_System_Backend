package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abridged.forestrymanagementsystem.dao.ContractDao;
import com.abridged.forestrymanagementsystem.dto.Contract;
import com.abridged.forestrymanagementsystem.dto.ContractResponse;
import com.abridged.forestrymanagementsystem.validation.ValidationImpl;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao dao;

	static ValidationImpl validation = new ValidationImpl();

	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	boolean flag6 = false;
	boolean flag7 = false;

	@Override
	public Contract serviceGetContract(String contractNumber, ContractResponse contractResponse) {
		if (validation.idValidation(contractNumber)) {
			contractResponse.setStatus(200);
			return dao.getContract(contractNumber);
		} else {
			contractResponse.setStatus(401);
			contractResponse.setMessage1("Please enter valid contract number (e.g range{1,4})");
		}
		return null;
	}

	@Override
	public boolean serviceAddContract(Contract contract, ContractResponse contractResponse) {
		if (validation.idValidation(contract.getContractNumber())) {
			flag1 = true;
		} else {
			contractResponse.setMessage1("Please enter valid contract number (e.g range{1,4})");
		}
		if (validation.idValidation(contract.getCustomerId())) {
			flag2 = true;
		} else {
			contractResponse.setMessage2("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.idValidation(contract.getProductId())) {
			flag3 = true;
		} else {
			contractResponse.setMessage3("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.townValidation(contract.getDeliveryPlace())) {
			flag4 = true;
		} else {
			contractResponse.setMessage4("Please enter valid delivery place (e.g Mumbai)");
		}
		if (validation.dateValidation(contract.getDeliveryDate())) {
			flag5 = true;
		} else {
			contractResponse.setMessage5("Please enter valid delivery date (e.g 2020-10-30)");
		}
		if (validation.quantityValidation(contract.getQuantity())) {
			flag6 = true;
		} else {
			contractResponse.setMessage6("Please enter valid quantity (e.g 20)");
		}
		if (validation.idValidation(contract.getSchedulerId())) {
			flag7 = true;
		} else {
			contractResponse.setMessage7("Please enter valid scheduler id (e.g range{1,4})");
		}
		
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7) {
			contractResponse.setStatus(200);
			return dao.addContract(contract);
		} else {
			contractResponse.setStatus(401);
			contractResponse.setMessage8("please check contract properties");
		}
		return false;
	}

	@Override
	public boolean serviceUpdateContract(Contract contract, ContractResponse contractResponse) {
		if (validation.idValidation(contract.getContractNumber())) {
			flag1 = true;
		} else {
			contractResponse.setMessage1("Please enter valid contract number (e.g range{1,4})");
		}
		if (validation.idValidation(contract.getCustomerId())) {
			flag2 = true;
		} else {
			contractResponse.setMessage2("Please enter valid customer id (e.g range{1,4})");
		}
		if (validation.idValidation(contract.getProductId())) {
			flag3 = true;
		} else {
			contractResponse.setMessage3("Please enter valid product id (e.g range{1,4})");
		}
		if (validation.townValidation(contract.getDeliveryPlace())) {
			flag4 = true;
		} else {
			contractResponse.setMessage4("Please enter valid delivery place (e.g Mumbai)");
		}
		if (validation.dateValidation(contract.getDeliveryDate())) {
			flag5 = true;
		} else {
			contractResponse.setMessage5("Please enter valid delivery date (e.g 2020-10-30)");
		}
		if (validation.quantityValidation(contract.getQuantity())) {
			flag6 = true;
		} else {
			contractResponse.setMessage6("Please enter valid quantity (e.g 20)");
		}
		if (validation.idValidation(contract.getSchedulerId())) {
			flag7 = true;
		} else {
			contractResponse.setMessage7("Please enter valid scheduler id (e.g range{1,4})");
		}
		
		if (flag1 && flag2 && flag3 && flag4 && flag4 && flag5 && flag6 && flag7) {
			contractResponse.setStatus(200);
			return dao.updateContract(contract);
		} else {
			contractResponse.setStatus(401);
			contractResponse.setMessage8("please check contract properties");
		}
		return false;
	}

	@Override
	public boolean serviceDeleteContract(String contractNumber, ContractResponse contractResponse) {
		if (validation.idValidation(contractNumber)) {
			contractResponse.setStatus(200);
			return dao.deleteContract(contractNumber);
		} else {
			contractResponse.setStatus(401);
			contractResponse.setMessage1("Please enter valid contract id (e.g range{1,4})");
		}
		return false;
	}

	@Override
	public List<Contract> serviceGetAllContracts() {
		return dao.getAllContracts();
	}

}
